/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../services/util.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Endereco} from '../../../model/domain/endereco.model';
import { Correio } from 'src/model/domain/correio.model';
import { Status } from 'src/model/domain/status.model';

@Component({
  selector: 'app-select-address',
  templateUrl: './select-address.component.html',
  styleUrls: ['./select-address.component.css']
})
export class SelectAddressComponent implements OnInit {

  constructor(private service: UtilService,
              private router: Router) { }

  enderecos: Endereco[];

  endereco: Endereco = new Endereco();

  correio: Correio;

  dataEntrega: Date;

  mensagens = [];

  ngOnInit(): void {
    const status: Status = new Status();
    status.id = 1;
    this.endereco.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.endereco.status = status;
    this.correio = null;
    this.getEnderecos();
  }

  async getEnderecos() {
    await this.service.get( this.endereco, 'enderecos').subscribe( resultado => {
      this.enderecos = resultado?.entidades;
    });
  }

  getCustoPrazo(endereco: Endereco){
    this.service.get(endereco, 'correios').subscribe(resultado => {
      this.correio = resultado?.entidades[0];
      sessionStorage.setItem('custoFrete', JSON.stringify(this.correio?.valorCusto));
      sessionStorage.setItem('prazo', JSON.stringify(this.correio?.quantidadeDiasEntrega));
    });
  }

  seleciona(endereco: Endereco) {
    this.mensagens = [];
    this.endereco = endereco;
    sessionStorage.setItem('enderecoSelecionado', JSON.stringify(endereco));
    this.getCustoPrazo(endereco);
  }

  continua() {
    this.mensagens = [];
    if (this.endereco.logradouro != null) {
      this.router.navigate(['/app-logado/select-creditcard']);
    } else {
      // tslint:disable-next-line:max-line-length
      this.mensagens.push('É necessário selecionar um endereço, caso não tenha o endereço desejado, basta cadastra-lo clicando em "Adicionar novo endereço"');
    }
  }

  cancelaCompra() {

    sessionStorage.removeItem('cartoesSelecionados');

    sessionStorage.removeItem('valorTotal');

    sessionStorage.removeItem('carrinho');

    sessionStorage.removeItem('enderecoSelecionado');

    this.router.navigate(['/app-logado/product-market-page']);
  }
}
