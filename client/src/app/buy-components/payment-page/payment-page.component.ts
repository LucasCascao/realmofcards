import { Component, OnInit } from '@angular/core';
import { UtilService } from 'src/services/util.service';
import { Endereco } from 'src/model/domain/endereco.model';
import {Util} from '../../shared/app.util';
import { CartaoCredito } from 'src/model/domain/cartao-credito.model';
import { Carta } from 'src/model/domain/carta.model';
import { async } from '@angular/core/testing';
import { PaymentPage } from 'src/model/page-data/payment-page';

@Component({
  selector: 'app-payment-page',
  templateUrl: './payment-page.component.html',
  styleUrls: ['./payment-page.component.css']
})
export class PaymentPageComponent implements OnInit {

  constructor(private service: UtilService, private serviceCartao: UtilService, public util: Util) { }

  paymentPageData: PaymentPage;

  cartaoOpcao1: CartaoCredito;

  cartaoOpcao2: CartaoCredito;

  valores;

  valorTotal: number;

  ngOnInit(): void {

    this.paymentPageData = new PaymentPage();
    this.paymentPageData.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));

    this.getPaymentPage();
  }

  private getPaymentPage(){
    this.service.get(this.paymentPageData, 'paymentpage').subscribe( resultado => {
      this.paymentPageData = resultado?.entidades[0];
      this.getCartoes();
      this.calculaValorTotal();
    });
  }

  private getCartoes() {
    this.cartaoOpcao1 = this.paymentPageData?.cartaoCreditoList?.pop();
    this.cartaoOpcao2 = this.paymentPageData?.cartaoCreditoList?.pop();
  }

  private calculaValorTotal() {

    this.valorTotal = 0;

    this.paymentPageData.carrinho.itens.forEach( item => {
      this.valorTotal += item.carta.valorVenda;
    });

    this.valores = [];
    this.valores.push(this.valorTotal.toFixed(2));
    this.valores.push((this.valorTotal / 2).toFixed(2));
    this.valores.push((this.valorTotal / 3).toFixed(2));

  }
}
