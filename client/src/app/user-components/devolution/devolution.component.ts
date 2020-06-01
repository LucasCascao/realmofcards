import { Component, OnInit } from '@angular/core';
import {MockCards} from '../../../mock/mock-card.model';
import {MockClient} from '../../../mock/mock-cliente.model';
import { Pedido } from 'src/model/domain/pedido.model';
import { Transicao } from 'src/model/domain/transicao.model';
import { ItemTransicao } from 'src/model/domain/item-transicao.model';
import { UtilService } from 'src/services/util.service';
import { Router } from '@angular/router';
import { Util } from 'src/app/shared/app.util';
import { StatusPedido } from 'src/model/domain/status-pedido.model';
import { TipoTransicao } from 'src/model/domain/tipo-transicao';
import { StatusTransacao } from 'src/model/domain/status-transicao';

@Component({
  selector: 'app-devolution',
  templateUrl: './devolution.component.html',
  styleUrls: ['./devolution.component.css']
})
export class DevolutionComponent implements OnInit {

  pedido: Pedido;

  devolucao: Transicao;

  mensagens = []

  constructor(private service: UtilService,
              private router: Router,
              private util: Util) { }

  ngOnInit(): void {
    this.pedido = JSON.parse(sessionStorage.getItem('pedidoSelecionado'));
    console.log(this.pedido);
    this.devolucao = new Transicao();
    this.devolucao.pedido = this.pedido;
    this.devolucao.itemTransicaoList = new Array<ItemTransicao>();
    this.devolucao.subTotal = this.pedido?.valorTotal;
    this.adcionaItensNaDevolucao();
    console.log(this.devolucao);
  }

  adcionaItensNaDevolucao() {
    this.pedido.itemList.forEach( item => {
      let itemDevolucao: ItemTransicao = new ItemTransicao();
      itemDevolucao.item = item;
      itemDevolucao.quantidade = item.quantidade;
      this.devolucao.itemTransicaoList.push(itemDevolucao);
    });
  }

  calculaValorTotalItem(valor: number, quantidade: number) {
    return valor * quantidade;
  }

  trocaProduto() {
    this.mensagens = [];
    if(this.devolucao.itemTransicaoList.length > 0){
      let statusPedido: StatusPedido = new StatusPedido();
      statusPedido.id = 11;
      let tipoTransicao: TipoTransicao = new TipoTransicao();
      tipoTransicao.id = 2
      let statusTransicao: StatusTransacao = new StatusTransacao();
      statusTransicao.id = 1;
      this.devolucao.pedido.statusPedido = statusPedido;
      this.devolucao.statusTransacao = statusTransicao;
      this.devolucao.tipoTransicao = tipoTransicao;
      this.service.add(this.devolucao, 'trocas').subscribe(resultado => {
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
