/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {Pedido} from '../../../model/domain/pedido.model';
import {UtilService} from '../../../services/util.service';
import {Troca} from '../../../model/domain/troca.model';
import {ItemTroca} from '../../../model/domain/item-troca.model';
import {Router} from "@angular/router";
import {StatusPedido} from "../../../model/domain/status-pedido.model";

@Component({
  selector: 'app-product-trade',
  templateUrl: './product-trade.component.html'
})
export class ProductTradeComponent implements OnInit {

  pedido: Pedido;

  troca: Troca;

  quantidade: number;

  trocaItemList: Array<ItemTroca>;

  constructor(private service: UtilService,
              private router: Router) { }

  ngOnInit(): void {
    this.trocaItemList = new Array<ItemTroca>();
    this.pedido = JSON.parse(sessionStorage.getItem('pedidoSelecionado'));
    this.troca = new Troca();
    this.troca.itemListParaTroca = new Array<ItemTroca>();
    this.troca.pedidoParaTroca = this.pedido;
    this.quantidade = 1;
    this.adcionaItensNaTrocaNoInicio();
  }

  adcionaItensNaTrocaNoInicio() {
    this.pedido.itemList.forEach( item => {
      const itemTroca: ItemTroca = new ItemTroca();
      itemTroca.itemParaTroca = item;
      itemTroca.quantidade = 1;
      this.trocaItemList.push(itemTroca);
    });
  }

  calculaValorTotalItew(valor: number, quantidade: number) {
    return valor * quantidade;
  }

  adicionaItem(itemTrocaRecebida: ItemTroca, event, quantidade) {
    if (event.target.checked) {
      this.troca.itemListParaTroca.push(itemTrocaRecebida);
    } else {
      const itemTrocaList = new Array<ItemTroca>();
      this.troca.itemListParaTroca.forEach( itemTroca => {
        if (itemTroca.itemParaTroca.id !== itemTrocaRecebida.itemParaTroca.id) {
          itemTrocaList.push(itemTroca);
        }
      });
      this.troca.itemListParaTroca = itemTrocaList;
    }
    console.log(this.troca?.itemListParaTroca);
  }

  trocaProduto() {
    const statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 4;
    this.troca.pedidoParaTroca.statusPedido = statusPedido;
    this.service.add(this.troca, 'trocas').subscribe(() => {
      this.router.navigate(['/app-logado/user-orders']);
    });
  }
}
