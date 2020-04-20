import { Component, OnInit } from '@angular/core';
import { UtilService } from 'src/services/util.service';
import { Endereco } from 'src/model/domain/endereco.model';
import {Util} from '../../shared/app.util';
import { CartaoCredito } from 'src/model/domain/cartao-credito.model';
import { Carta } from 'src/model/domain/carta.model';
import { async } from '@angular/core/testing';
import { PaymentPage } from 'src/model/page-data/payment-page';
import {Router} from "@angular/router";

@Component({
  selector: 'app-payment-page',
  templateUrl: './payment-page.component.html',
  styleUrls: ['./payment-page.component.css']
})
export class PaymentPageComponent implements OnInit {

  constructor(private service: UtilService,
              public util: Util,
              private router: Router) { }

  paymentPageData: PaymentPage;

  cartaoOpcao1: CartaoCredito;

  cartaoOpcao2: CartaoCredito;

  valores: [];

  valorTotal: number;

  ngOnInit(): void {

    this.paymentPageData = new PaymentPage();
    this.paymentPageData.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));

    this.getPaymentPage();
  }

  private getPaymentPage() {
    this.service.get(this.paymentPageData, 'paymentpage').subscribe( resultado => {
      this.paymentPageData = resultado?.entidades[resultado?.entidades.length - 1];
      this.getCartoes();
      this.calculaValorTotal();
    });
  }

  private getCartoes() {
    this.cartaoOpcao1 = this.paymentPageData?.cartaoCreditoList[0];
    this.cartaoOpcao2 = this.paymentPageData?.cartaoCreditoList[1];
  }

  private calculaValorTotal() {

    this.valorTotal = 0;

    this.paymentPageData.carrinho.itemList.forEach(item => {
      this.valorTotal += (item?.carta?.valorVenda * item?.quantidade);
    });

    this.valores = [];
    // @ts-ignore
    this.valores.push(this.valorTotal.toFixed(2));
    // @ts-ignore
    this.valores.push((this.valorTotal / 2).toFixed(2));
    // @ts-ignore
    this.valores.push((this.valorTotal / 3).toFixed(2));

  }

  selecionaCartao(id: number) {
    sessionStorage.setItem('idCartaoSelecionado', id.toString());
    this.router.navigate(['/app-logado/creditcard-list']);
  }

  selecionaEndereco(id: number) {
    sessionStorage.setItem('idEnderecoSelecionado', id.toString())
    this.router.navigate(['/app-logado/address-list']);
  }
}
