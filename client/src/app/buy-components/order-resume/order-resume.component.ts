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

  constructor(private service: UtilService,
              private router: Router) { }

  cartoes: Array<CartaoCredito>;

  endereco: Endereco;

  carrinho: Carrinho;

  valorTotal: number;

  ngOnInit(): void {

    this.cartoes = JSON.parse(sessionStorage.getItem('cartoesSelecionados'));

    this.valorTotal = JSON.parse(sessionStorage.getItem('valorTotal'));

    this.carrinho = JSON.parse(sessionStorage.getItem('carrinho'));

    this.endereco = JSON.parse(sessionStorage.getItem('enderecoSelecionado'));

  }

  confirmarCompra() {

    const formaPagamento: FormaPagamento = new FormaPagamento();

    this.cartoes?.forEach(cartao => {
      formaPagamento.cartaoCreditoList.push(cartao);
    });

    const statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 1;

    const pedido: Pedido = new Pedido();
    pedido.cliente = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    pedido.formaPagamento = formaPagamento;
    pedido.itemList = this.carrinho?.itens;
    pedido.endereco = this.endereco;
    pedido.statusPedido = statusPedido;
    pedido.valorTotal = this.valorTotal;

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
