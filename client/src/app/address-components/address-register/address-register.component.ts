/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../services/util.service';
import {Router} from '@angular/router';
import {Endereco} from '../../../model/domain/endereco.model';
import {Util} from '../../shared/app.util';
import { Estado } from 'src/model/domain/estado.model';
import { Cidade } from 'src/model/domain/cidade.model';

@Component({
  selector: 'app-address-register',
  templateUrl: './address-register.component.html',
  styleUrls: ['./address-register.component.css']
})
export class AddressRegisterComponent implements OnInit {

  endereco: Endereco;

  estados: Estado[];

  mensagens = [];

  constructor(private service: UtilService, private router: Router, private util: Util) { }

  ngOnInit(): void {
    this.endereco = new Endereco();
    this.endereco.cidade = new Cidade();
    this.endereco.cidade.estado = new Estado();
    this.endereco.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.endereco.preferido = false;
    this.estados = [];
    this.getEstados();
  }

  getEstados() {
    this.service.get(new Estado(), 'estados').subscribe( resultado => {
      this.estados = resultado?.entidades;
    });
  }

  cadastra() {
    this.mensagens = [];
    this.service.add(this.endereco, 'enderecos').subscribe(resultado => {
      if (resultado.msg == null) {
        this.router.navigate(['/app-logado/select-address']);
      } else {
        this.mensagens = this.util.getMensagensSeparadas2(resultado?.msg);
      }
    });
  }
}
