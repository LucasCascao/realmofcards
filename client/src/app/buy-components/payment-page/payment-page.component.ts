import { Component, OnInit } from '@angular/core';
import { UtilService } from 'src/services/util.service';
import { Endereco } from 'src/model/domain/endereco.model';
import {Util} from '../../shared/app.util';
import { CartaoCredito } from 'src/model/domain/cartao-credito.model';
import { Carta } from 'src/model/domain/carta.model';
import { async } from '@angular/core/testing';

@Component({
  selector: 'app-payment-page',
  templateUrl: './payment-page.component.html',
  styleUrls: ['./payment-page.component.css']
})
export class PaymentPageComponent implements OnInit {

  constructor(private service: UtilService, private serviceCartao: UtilService, public util: Util) { }

  endereco: Endereco;

  cartao1: CartaoCredito;

  cartao2: CartaoCredito;

  ngOnInit(): void {
    this.endereco = new Endereco();
    this.endereco.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.endereco.preferido = true;
    this.getEndereco();

    this.cartao1 = new CartaoCredito();
    this.cartao1.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.cartao1.preferido = true;
  }

  getEndereco(){
    this.service.get(this.endereco, 'enderecos').subscribe( resultado => {
      this.endereco = resultado?.entidades[0];
      this.getCartaoCredito();
    });
  }

  getCartaoCredito(){
    this.serviceCartao.get(this.cartao1, 'cartaocredito').subscribe( resultado => {
      this.cartao1 = resultado?.entidades[0];
      this.cartao2 = resultado?.entidades[1];
    });
  }

}
