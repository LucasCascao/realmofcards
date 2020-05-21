/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../services/util.service';
import {CartaoCredito} from '../../../model/domain/cartao-credito.model';
import {Router} from "@angular/router";
import { FormaPagamento } from 'src/model/domain/forma-pagamento.model';
import { Cupom } from 'src/model/domain/cupom.model';
import { Util } from 'src/app/shared/app.util';

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

  valorTotal: number;

  cupom: Cupom;

  mensagens = [];

  mensagemCupom = [];

  ngOnInit(): void {

    this.valorTotal = JSON.parse(sessionStorage.getItem('valorTotal'));
    let custoFrete = JSON.parse(sessionStorage.getItem('custoFrete'));

    this.valorTotal += custoFrete;

    // this.cartoesSelecionados = [];
    this.formaPagamentoList = [];
    this.formaPagamentoSelecionadoList = [];

    this.cupom = new Cupom();

    const cartaoCredito = new CartaoCredito();
    const pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    cartaoCredito.pessoa = pessoa;

    this.getCartoes(cartaoCredito);
  }

  getCartoes(cartaoCredito: CartaoCredito) {
    this.service.get(cartaoCredito, 'cartaocredito').subscribe( resultado => {
      this.cartoes = resultado?.entidades;
      this.cartoes.forEach(cartao => {
        let formaPagamento: FormaPagamento = new FormaPagamento();
        formaPagamento.registroCartao = cartao.numero;
        formaPagamento.cartao = cartao;
        formaPagamento.valorPagamento = 0;
        formaPagamento.isSelecionado = false;
        this.formaPagamentoList.push(formaPagamento);
      });
    });
  }

  calculaValor(formaPagamentoSelecionado: FormaPagamento){
    if(this.formaPagamentoSelecionadoList.length == 2) {
      this.formaPagamentoSelecionadoList.forEach( formaPagamento => {
        if(formaPagamento?.cartao?.id == formaPagamentoSelecionado?.cartao?.id){
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
        if (this.formaPagamentoSelecionadoList.length == 0) {
          formaPagamentoSelecionado.valorPagamento = this.valorTotal;
        }
        formaPagamentoSelecionado.isSelecionado = true;
        this.formaPagamentoSelecionadoList.push(formaPagamentoSelecionado);
      } else {
        this.mensagens.push('Não é possivel selecionar mais que dois cartões.');
        event.target.checked = false;
      }
    } else {
      formaPagamentoSelecionado.isSelecionado = false;
      const formaPagamentoSemFormaSelecionada: Array<FormaPagamento> = new Array<FormaPagamento>();
      this.formaPagamentoSelecionadoList.forEach( FormaPagamento => {
        if (FormaPagamento.cartao.id !== formaPagamentoSelecionado.cartao.id) {
          FormaPagamento.valorPagamento = this.valorTotal;
          formaPagamentoSemFormaSelecionada.push(FormaPagamento);
        } else {
          formaPagamentoSelecionado.valorPagamento = 0;
        }
      });
      this.formaPagamentoSelecionadoList = formaPagamentoSemFormaSelecionada;
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
      this.mensagens.push('É necessário escolher pelo menos um cartão.');
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
      valorAcumulado += formaPagamento.valorPagamento;
    })

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
    this.service.get(this.cupom, '/cupons').subscribe( resultado => {
      if(resultado?.msg != null){
        this.mensagemCupom = this.util.getMensagensSeparadas2(resultado?.msg);
        this.cupom.valor = null;
      } else {
        this.cupom = resultado?.entidades[0];
      }
    });
  }
}
