/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {Pedido} from '../../../model/domain/pedido.model';
import {UtilService} from '../../../services/util.service';
import {Transicao} from '../../../model/domain/transicao.model';
import {ItemTransicao} from '../../../model/domain/item-transicao.model';
import {Router} from "@angular/router";
import {StatusPedido} from "../../../model/domain/status-pedido.model";
import { Util } from 'src/app/shared/app.util';
import { TipoTransicao } from 'src/model/domain/tipo-transicao';
import { StatusTransacao } from 'src/model/domain/status-transicao';

@Component({
  selector: 'app-product-trade',
  templateUrl: './product-trade.component.html'
})
export class ProductTradeComponent implements OnInit {

  pedido: Pedido;

  troca: Transicao;

  quantidade: number;

  trocaItemList: Array<ItemTransicao>;

  mensagens = []

  constructor(private service: UtilService,
              private router: Router,
              private util: Util) { }

  ngOnInit(): void {
    this.trocaItemList = new Array<ItemTransicao>();
    this.pedido = JSON.parse(sessionStorage.getItem('pedidoSelecionado'));
    console.log(this.pedido);
    this.troca = new Transicao();
    this.troca.itemTransicaoList = new Array<ItemTransicao>();
    this.troca.pedido = this.pedido;
    this.quantidade = 1;
    this.adcionaItensNaTrocaNoInicio();
  }

  adcionaItensNaTrocaNoInicio() {
    this.pedido.itemList.forEach( item => {
      const itemTroca: ItemTransicao = new ItemTransicao();
      itemTroca.item = item;
      itemTroca.quantidade = 1;
      this.trocaItemList.push(itemTroca);
    });
  }

  calculaValorTotalItem(valor: number, quantidade: number) {
    return valor * quantidade;
  }

  adicionaItem(itemTrocaRecebida: ItemTransicao, event, quantidade) {
    this.mensagens = [];
    if (event.target.checked) {
      if(quantidade > 0 && quantidade <= itemTrocaRecebida?.item?.quantidade){
        this.troca.itemTransicaoList.push(itemTrocaRecebida);
      }else{
        this.mensagens.push('Quandidade tem que ser maior que 0 e menor que a quantidade comprada.');
        event.target.checked = false;
      }
    } else {
      const itemTrocaList = new Array<ItemTransicao>();
      this.troca.itemTransicaoList.forEach( itemTroca => {
        if (itemTroca.item.id !== itemTrocaRecebida.item.id) {
          itemTrocaList.push(itemTroca);
        }
      });
      this.troca.itemTransicaoList = itemTrocaList;
    }
  }

  trocaProduto() {
    this.mensagens = [];
    if(this.troca.itemTransicaoList.length > 0){
      let statusPedido: StatusPedido = new StatusPedido();
      statusPedido.id = 7;
      let tipoTransicao: TipoTransicao = new TipoTransicao();
      tipoTransicao.id = 1
      let statusTransicao: StatusTransacao = new StatusTransacao();
      statusTransicao.id = 1;
      this.troca.pedido.statusPedido = statusPedido;
      this.troca.statusTransacao = statusTransicao;
      this.troca.tipoTransicao = tipoTransicao;
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
