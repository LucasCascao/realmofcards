import { Component, OnInit } from '@angular/core';
import {UtilService} from "../../../services/util.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Endereco} from "../../../model/domain/endereco.model";

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

  ngOnInit(): void {
    this.endereco.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.getEnderecos();
  }

  async getEnderecos() {
    await this.service.get( this.endereco, 'enderecos').subscribe( resultado => {
      this.enderecos = resultado?.entidades;
    });
  }

  seleciona(endereco: Endereco) {
    this.endereco = endereco;
    sessionStorage.setItem('enderecoSelecionado', JSON.stringify(endereco));
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
