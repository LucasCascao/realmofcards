/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../services/util.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Endereco} from '../../../model/domain/endereco.model';
import {Pessoa} from '../../../model/domain/pessoa.model';
import {Util} from '../../shared/app.util';
import {Estado} from '../../../model/domain/estado.model';
import { Cidade } from 'src/model/domain/cidade.model';
import * as Inputmask from 'inputmask';

@Component({
  selector: 'app-address-alter',
  templateUrl: './address-alter.component.html',
  styleUrls: ['./address-alter.component.css']
})
export class AddressAlterComponent implements OnInit {

  constructor(private service: UtilService, private route: ActivatedRoute, private router: Router, private util: Util) { }

  endereco: Endereco = new Endereco();

  estados: Estado[];

  cidades: Cidade[];

  estadoSelecionado: Estado;

  mensagens = [];

  ngOnInit(): void {
    Inputmask().mask(document.querySelectorAll('input'));
    this.endereco = JSON.parse(sessionStorage.getItem('enderecoSelecionado'));
    this.endereco.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.estados = [];
    this.estadoSelecionado = new Estado();
    this.getEstados();
  }

  getEstados() {
    this.service.get(new Estado(), 'estados').subscribe( resultado => {
      this.estados = resultado?.entidades;
      this.getEnderecos();
    });
  }

  getEnderecos() {
    this.service.get( this.endereco, 'enderecos').subscribe( resultado => {
      this.endereco = resultado?.entidades[0];
    });
  }

  enviarPara(){
    let paginaRetorno = JSON.parse(sessionStorage.getItem('paginaParaRetorno'));
    this.router.navigate([paginaRetorno]);
  }

  async alterarEndereco() {
    this.mensagens = [];
    await this.service.update(this.endereco, 'enderecos').subscribe(resultado => {
      if (resultado.msg == null) {
        this.enviarPara();
      } else {
        this.mensagens = this.util.getMensagensSeparadas2(resultado?.msg);
      }
    });
  }

  chamaCidade(){
    this.endereco.cidade.estado = this.estadoSelecionado;
    this.service.get( this.endereco.cidade, 'cidades').subscribe( resultado => {
      this.cidades = resultado?.entidades;
    });
  }
}
