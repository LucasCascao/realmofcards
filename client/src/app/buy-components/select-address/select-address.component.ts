/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../services/util.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Endereco} from '../../../model/domain/endereco.model';
import { Correio } from 'src/model/domain/correio.model';

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

  ngOnInit(): void {
    this.endereco.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
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
    this.endereco = endereco;
    sessionStorage.setItem('enderecoSelecionado', JSON.stringify(endereco));
    this.getCustoPrazo(endereco);
  }

  continua() {
    if (this.endereco.logradouro != null) {
      this.router.navigate(['/app-logado/select-creditcard']);
    } else {
      // tslint:disable-next-line:max-line-length
      alert('É necessário selecionar um endereço, caso não tenha o endereço desejado, basta cadastra-lo clicando em "Cadastrar novo endereco"');
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
