/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import { Pessoa } from 'src/model/domain/pessoa.model';
import {Router} from '@angular/router';
import {Util} from '../../shared/app.util';
import {Usuario} from '../../../model/domain/usuario.model';
import {TipoUsuario} from '../../../model/domain/tipo-usuario';
import { UtilService } from 'src/services/util.service';
import { Status } from 'src/model/domain/status.model';
import * as Inputmask from 'inputmask';
import { Endereco } from 'src/model/domain/endereco.model';
import { Cidade } from 'src/model/domain/cidade.model';
import { Estado } from 'src/model/domain/estado.model';
import { Telefone } from 'src/model/domain/telefone.model';
import { TipoTelefone } from 'src/model/domain/tipo-telefone';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  user: Usuario = new Usuario();

  person: Pessoa = new Pessoa();

  telefone: Telefone = new Telefone;

  tiposTelefone : TipoTelefone[];

  dataNascimento: string;

  mensagens = [];

  constructor( private service: UtilService, private router: Router, private util: Util) { }

  ngOnInit(): void {
    Inputmask().mask(document.querySelectorAll('input'));
    this.getTiposTelefone();
  }

  getTiposTelefone(){
    this.service.get(new TipoTelefone, 'tipos-telefone').subscribe(resultado => this.tiposTelefone = resultado?.entidades);
  }

  cadastra() {
    this.person.usuario = this.user;
    this.person.usuario.tipoUsuario = new TipoUsuario();
    this.person.usuario.tipoUsuario.id = 2;
    this.person.usuario.status = new Status();
    this.person.usuario.status.id = 1;
    this.person.dataNascimento = this.util.formatarDataJSON(this.dataNascimento);
    this.person.telefones = [];
    this.person.telefones.push(this.telefone);
    this.cadastraPessoa();
  }

  cadastraPessoa() {
    this.mensagens = [];
    this.service.add(this.person, 'pessoas').subscribe(resultado => {
      if (resultado.msg !== null) {
        this.mensagens = this.util.getMensagensSeparadas2(resultado?.msg);
      } else {
        this.router.navigate(['/']);
      }
    });
  }
}
