/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {Pessoa} from '../../../model/domain/pessoa.model';
import {ActivatedRoute, Router} from '@angular/router';
import {Util} from '../../shared/app.util';
import {Usuario} from '../../../model/domain/usuario.model';
import { UtilService } from 'src/services/util.service';
import * as Inputmask from 'inputmask';

@Component({
  selector: 'app-user-alter',
  templateUrl: './user-alter.component.html',
  styleUrls: ['./user-alter.component.css']
})
export class UserAlterComponent implements OnInit {

  client: Pessoa = new Pessoa();
  user: Usuario = new Usuario();
  dataNascimento: string;
  mensagens = [];

  constructor(private service: UtilService, private router: Router, private route: ActivatedRoute, private util: Util) { }

  ngOnInit(): void {
    Inputmask().mask(document.querySelectorAll('input'));
    this.client = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.getCliente();
  }

  getCliente() {
    this.service.get(this.client, 'pessoas').subscribe( dado => {
        this.client = dado.entidades[0];
        this.dataNascimento = this.util.formatarDataComum(this.client.dataNascimento);
    });
  }

  alterarCliente() {
    this.mensagens = []
    const dataFormatada = this.util.formatarDataJSON(this.dataNascimento);
    this.client.dataNascimento = dataFormatada != null ? new Date(dataFormatada) : null;
    this.service.update(this.client, 'pessoas').subscribe(
      resultado => {
        if (resultado.msg == null) {
          this.client = resultado.entidades[0];
          this.router.navigate(['/app-logado/user-details']);
        } else {
          this.mensagens = this.util.getMensagensSeparadas2(resultado?.msg);
        }
      }
    );
  }
}
