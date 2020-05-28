/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {Pedido} from '../../../model/domain/pedido.model';
import {UtilService} from '../../../services/util.service';
import {Troca} from '../../../model/domain/troca.model';
import {ItemTroca} from '../../../model/domain/item-troca.model';
import {Router} from "@angular/router";
import {StatusPedido} from "../../../model/domain/status-pedido.model";
import { Util } from 'src/app/shared/app.util';

@Component({
  selector: 'app-product-trade',
  templateUrl: './product-trade.component.html'
})
export class ProductTradeComponent implements OnInit {

  pedido: Pedido;

  troca: Troca;

  quantidade: number;

  trocaItemList: Array<ItemTroca>;

  mensagens = []

  constructor(private service: UtilService,
              private router: Router,
              private util: Util) { }

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

  calculaValorTotalItem(valor: number, quantidade: number) {
    return valor * quantidade;
  }

  adicionaItem(itemTrocaRecebida: ItemTroca, event, quantidade) {
    this.mensagens = [];
    if (event.target.checked) {
      if(quantidade > 0 && quantidade <= itemTrocaRecebida?.itemParaTroca?.quantidade){
        this.troca.itemListParaTroca.push(itemTrocaRecebida);
      }else{
        this.mensagens.push('Quandidade tem que ser maior que 0 e menor que a quantidade comprada.');
        event.target.checked = false;
      }
    } else {
      const itemTrocaList = new Array<ItemTroca>();
      this.troca.itemListParaTroca.forEach( itemTroca => {
        if (itemTroca.itemParaTroca.id !== itemTrocaRecebida.itemParaTroca.id) {
          itemTrocaList.push(itemTroca);
        }
      });
      this.troca.itemListParaTroca = itemTrocaList;
    }
  }

  trocaProduto() {
    this.mensagens = [];
    if(this.troca.itemListParaTroca.length > 0){
      const statusPedido: StatusPedido = new StatusPedido();
      statusPedido.id = 7;
      this.troca.pedidoParaTroca.statusPedido = statusPedido;
      this.service.add(this.troca, 'trocas').subscribe(resultado => {
        if(resultado?.msg != null){
          this.mensagens = this.util.getMensagensSeparadas2(resultado?.msg);
        } else {
          this.router.navigate(['/app-logado/user-orders']);
        }
      });
    } else {
      this.mensagens.push('Deve ser selecionado pelo menos um produto.');
    }
  }
}
