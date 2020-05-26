/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../services/util.service';
import {CartaoCredito} from '../../../model/domain/cartao-credito.model';
import {Router} from "@angular/router";
import { FormaPagamento } from 'src/model/domain/forma-pagamento.model';
import { Cupom } from 'src/model/domain/cupom.model';
import { Util } from 'src/app/shared/app.util';
import { Status } from 'src/model/domain/status.model';

@Component({
  selector: 'app-select-credit-card',
  templateUrl: './select-credit-card.component.html',
  styleUrls: ['./select-credit-card.component.css']
})
export class SelectCreditCardComponent implements OnInit {

  constructor(private service: UtilService,
              private router: Router,
              private util: Util) { }

  cartoes: CartaoCredito[];

  // cartoesSelecionados: CartaoCredito[];

  formaPagamentoList: FormaPagamento[];

  formaPagamentoSelecionadoList: FormaPagamento[];

  formaPagamentoComCupom: FormaPagamento;

  valorTotal: number;

  mensagens = [];

  mensagemCupom = [];

  isMsgError = false;

  isFirstCartao = true;

  ngOnInit(): void {

    this.valorTotal = JSON.parse(sessionStorage.getItem('valorTotal'));
    let custoFrete = JSON.parse(sessionStorage.getItem('custoFrete'));

    this.valorTotal += custoFrete;

    // this.cartoesSelecionados = [];
    this.formaPagamentoList = [];
    this.formaPagamentoSelecionadoList = [];

    this.formaPagamentoComCupom = new FormaPagamento();
    this.formaPagamentoComCupom.cupom = new Cupom();

    const cartaoCredito = new CartaoCredito();
    const pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    const status = new Status();
    status.id = 1;
    cartaoCredito.pessoa = pessoa;
    cartaoCredito.status = status;

    this.getCartoes(cartaoCredito);
  }

  getCartoes(cartaoCredito: CartaoCredito) {
    this.service.get(cartaoCredito, 'cartaocredito').subscribe( resultado => {
      this.cartoes = resultado?.entidades;
      this.cartoes.forEach(cartao => {
        let formaPagamento: FormaPagamento = new FormaPagamento();
        formaPagamento.cartaoCredito = cartao;
        formaPagamento.valorPagamento = 0;
        this.formaPagamentoList.push(formaPagamento);
      });
    });
  }

  calculaValor(formaPagamentoSelecionado: FormaPagamento){
    if(this.formaPagamentoSelecionadoList.length == 2) {
      this.formaPagamentoSelecionadoList.forEach( formaPagamento => {
        if(formaPagamento?.cartaoCredito?.id == formaPagamentoSelecionado?.cartaoCredito?.id){
          formaPagamento.valorPagamento = Number.parseFloat(formaPagamentoSelecionado.valorPagamento.toFixed(2));
        } else {
          formaPagamento.valorPagamento = Number.parseFloat((this.valorTotal - formaPagamentoSelecionado.valorPagamento).toFixed(2));
        }
      })
    }
  }

  selecionaCartao(event, formaPagamentoSelecionado: FormaPagamento) {
    if (event.target.checked) {
      if (this.formaPagamentoSelecionadoList.length < 2) {
        if (formaPagamentoSelecionado.cartaoCredito != null && this.isFirstCartao) {
          formaPagamentoSelecionado.valorPagamento = this.valorTotal;
          this.isFirstCartao = false;
        }
        formaPagamentoSelecionado.isSelecionado = true;
        this.formaPagamentoSelecionadoList.push(formaPagamentoSelecionado);
      } else {
        this.mensagens = [];
        this.mensagens.push('Não é possivel selecionar mais que duas formas de pagamento.');
        event.target.checked = false;
      }
    } else {
      formaPagamentoSelecionado.isSelecionado = false;
      this.formaPagamentoSelecionadoList.splice(this.formaPagamentoSelecionadoList.indexOf(formaPagamentoSelecionado), 1);
      if(this.formaPagamentoSelecionadoList.length < 1){
        this.isFirstCartao = true;
      }
    }
    console.log(this.formaPagamentoSelecionadoList);
  }

  selecionaCupom(event, formaPagamentoSelecionado: FormaPagamento){
    if(event.target.checked && formaPagamentoSelecionado?.cupom?.valor != null){
      this.valorTotal -= this.formaPagamentoComCupom?.cupom?.valor;
      this.selecionaCartao(event, formaPagamentoSelecionado);
      formaPagamentoSelecionado.valorPagamento = this.formaPagamentoComCupom?.cupom?.valor;
    } else if(event.target.checked == false){
      this.valorTotal += this.formaPagamentoComCupom?.cupom?.valor;
      this.mensagemCupom = [];
      this.selecionaCartao(event, formaPagamentoSelecionado);
    } else {
      this.mensagemCupom = [];
      this.mensagemCupom.push("É necessário validar o cupom");
      event.target.checked = false;
    }
  }

  continua() {
    this.mensagens = [];
    if(this.validaQuantidadeCartao()){
      if(this.validarFormaPagamentoSelecionados()
        && this.validarValorTotalPorFormaPagamentoSelecionado()) {
        sessionStorage.setItem('formasPagamentoSelecionadas', JSON.stringify(this.formaPagamentoSelecionadoList));
        this.router.navigate(['/app-logado/order-resume']);
      }
    }
  }

  validaQuantidadeCartao(): boolean{
    if(this.formaPagamentoSelecionadoList.length > 0){
      return true;
    }else{
      this.mensagens.push('É necessário escolher pelo menos uma forma de pagamento.');
      return false;
    }
  }

  validarFormaPagamentoSelecionados(): boolean{
    let valorNaoZerado:boolean = true;
    this.formaPagamentoSelecionadoList.forEach(formaPagamento => {
      if(formaPagamento.valorPagamento < 0.01){
        valorNaoZerado = false;
      }
    })

    if(!valorNaoZerado){
      this.mensagens.push('Valor de pagamento do cartao(s) está invalido');
    }

    return valorNaoZerado;
  }

  validarValorTotalPorFormaPagamentoSelecionado(): boolean{
    let valorAcumulado: number = 0;
    this.formaPagamentoSelecionadoList.forEach(formaPagamento => {
      if(formaPagamento?.cartaoCredito != null){
        valorAcumulado += formaPagamento.valorPagamento;
      }
    });

    if(valorAcumulado !== this.valorTotal){
      this.mensagens.push('Valor de pagamento do cartao(s) não está igual a R$ ' + this.valorTotal.toFixed(2) + '.');
    }

    return valorAcumulado == this.valorTotal;
  }

  selecionaCartaoExcluir(cartao: CartaoCredito) {
    sessionStorage.setItem('cartaoSelecionado', JSON.stringify(cartao));
  }

  validaCupom(){
    this.mensagemCupom = [];
    this.service.get(this.formaPagamentoComCupom.cupom, '/cupons').subscribe( resultado => {
      if(resultado?.msg != null){
        this.mensagemCupom = this.util.getMensagensSeparadas2(resultado?.msg);
        this.formaPagamentoComCupom.cupom.valor = null;
        this.isMsgError = true;
      } else {
        this.mensagemCupom.push("Cupom válido")
        this.formaPagamentoComCupom.cupom = resultado?.entidades[0];
        this.isMsgError = false;
      }
    });
  }
}
