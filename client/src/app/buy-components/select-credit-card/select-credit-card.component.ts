/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../services/util.service';
import {CartaoCredito} from '../../../model/domain/cartao-credito.model';
import {Router} from "@angular/router";
import { FormaPagamento } from 'src/model/domain/forma-pagamento.model';

@Component({
  selector: 'app-select-credit-card',
  templateUrl: './select-credit-card.component.html',
  styleUrls: ['./select-credit-card.component.css']
})
export class SelectCreditCardComponent implements OnInit {

  constructor(private service: UtilService,
              private router: Router) { }

  cartoes: CartaoCredito[];

  // cartoesSelecionados: CartaoCredito[];

  formaPagamentoList: FormaPagamento[];

  formaPagamentoSelecionadoList: FormaPagamento[];

  valorTotal: number;

  mensagemDeErro: string;


  ngOnInit(): void {

    this.valorTotal = JSON.parse(sessionStorage.getItem('valorTotal'));
    let custoFrete = JSON.parse(sessionStorage.getItem('custoFrete'));

    this.valorTotal += custoFrete;

    this.mensagemDeErro = '';

    // this.cartoesSelecionados = [];
    this.formaPagamentoList = [];
    this.formaPagamentoSelecionadoList = [];

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
        alert('Não é possivel selecionar mais que dois cartões.');
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
    if(this.validaQuantidadeCartao()){
      if(this.validarFormaPagamentoSelecionados()
        && this.validarValorTotalPorFormaPagamentoSelecionado()) {
        sessionStorage.setItem('formasPagamentoSelecionadas', JSON.stringify(this.formaPagamentoSelecionadoList));
        this.router.navigate(['/app-logado/order-resume']);
      } else {
        alert(this.mensagemDeErro);
        this.mensagemDeErro = '';
      }
    } else {
      alert(this.mensagemDeErro);
      this.mensagemDeErro = '';
    }

  }

  validaQuantidadeCartao(): boolean{
    if(this.formaPagamentoSelecionadoList.length > 0){
      return true;
    }else{
      this.mensagemDeErro += 'É necessário escolher pelo menos um cartão.\n';
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
      this.mensagemDeErro += 'Valor de pagamento do cartao(s) está invalido \n';
    }

    return valorNaoZerado;
  }

  validarValorTotalPorFormaPagamentoSelecionado(): boolean{
    let valorAcumulado: number = 0;
    this.formaPagamentoSelecionadoList.forEach(formaPagamento => {
      valorAcumulado += formaPagamento.valorPagamento;
    })

    if(valorAcumulado !== this.valorTotal){
      this.mensagemDeErro +='Valor de pagamento do cartao(s) não está igual a R$ ' + this.valorTotal.toFixed(2) + '\n';
    }

    return valorAcumulado == this.valorTotal;
  }

}
