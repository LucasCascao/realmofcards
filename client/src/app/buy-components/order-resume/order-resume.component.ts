/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {Endereco} from '../../../model/domain/endereco.model';
import {Carrinho} from '../../../model/domain/carrinho.model';
import {CartaoCredito} from '../../../model/domain/cartao-credito.model';
import {Pedido} from '../../../model/domain/pedido.model';
import {FormaPagamento} from '../../../model/domain/forma-pagamento.model';
import {UtilService} from '../../../services/util.service';
import {StatusPedido} from '../../../model/domain/status-pedido.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-order-resume',
  templateUrl: './order-resume.component.html',
  styleUrls: ['./order-resume.component.css']
})
export class OrderResumeComponent implements OnInit {

  constructor(private service: UtilService, private router: Router) { }

  formaPagamentoList: Array<FormaPagamento>;

  endereco: Endereco;

  carrinho: Carrinho;

  valorTotal: number;

  custoFrete: number;

  prazo: number;

  ngOnInit(): void {

    this.formaPagamentoList = JSON.parse(sessionStorage.getItem('formasPagamentoSelecionadas'));

    console.log(this.formaPagamentoList);

    this.valorTotal = JSON.parse(sessionStorage.getItem('valorTotal'));

    this.carrinho = JSON.parse(sessionStorage.getItem('carrinho'));

    this.endereco = JSON.parse(sessionStorage.getItem('enderecoSelecionado'));

    this.custoFrete = JSON.parse(sessionStorage.getItem('custoFrete'));

    this.prazo = JSON.parse(sessionStorage.getItem('prazo'));

    this.valorTotal += this.custoFrete;

  }

  confirmarCompra() {

    const statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 1;

    const pedido: Pedido = new Pedido();
    pedido.cliente = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    pedido.itemList = this.carrinho?.itemList;
    pedido.endereco = this.endereco;
    pedido.statusPedido = statusPedido;
    pedido.valorTotal = Number.parseFloat(this.valorTotal?.toFixed(2));
    pedido.formaPagamentoList = this.formaPagamentoList;

    this.cadastraPedido(pedido);
  }

  cadastraPedido(pedido: Pedido) {
    this.service.add(pedido, 'pedidos').subscribe(() => {
      this.router.navigate(['/app-logado/buy-confirm']);
    });
  }

  cancelaCompra() {

    sessionStorage.removeItem('cartoesSelecionados');

    sessionStorage.removeItem('valorTotal');

    sessionStorage.removeItem('carrinho');

    sessionStorage.removeItem('enderecoSelecionado');

    this.router.navigate(['/app-logado/product-market-page']);
  }

}
