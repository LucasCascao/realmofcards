/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {MockClient} from '../../../../mock/mock-cliente.model';
import {MockCards} from '../../../../mock/mock-card.model';
import {Pedido} from '../../../../model/domain/pedido.model';
import {UtilService} from '../../../../services/util.service';
import {StatusPedido} from '../../../../model/domain/status-pedido.model';
import {Troca} from '../../../../model/domain/troca.model';

@Component({
  selector: 'app-request-trade',
  templateUrl: './request-trade.component.html',
  styleUrls: ['./request-trade.component.css']
})
export class RequestTradeComponent implements OnInit {

  trocas: Array<Troca>;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.getTrocas();
  }

  getTrocas() {
    const statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 4;
    const pedido = new Pedido();
    pedido.statusPedido = statusPedido;
    const troca: Troca = new Troca();
    troca.pedidoParaTroca = pedido;
    this.service.get(troca, 'trocas').subscribe(resultado => {
      this.trocas = resultado?.entidades;
    });
  }

  confirmaPedidoEntrege(pedido: Pedido) {
    const statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 3;
    pedido.statusPedido = statusPedido;
    this.service.update(pedido, 'pedidos').subscribe(() => {
      document.location.reload();
    });
  }

  aprovarRequisicaoTroca(troca: Troca){
    let status: StatusPedido = new StatusPedido();
    status.id = 6
    troca.pedidoParaTroca.statusPedido = status;
    this.service.update(troca, 'trocas').subscribe(() => {
      document.location.reload();
    });
  }

  rejeitarRequisicaoTroca(troca: Troca){
    let status: StatusPedido = new StatusPedido();
    status.id = 7
    troca.pedidoParaTroca.statusPedido = status;
    this.service.update(troca, 'trocas').subscribe(() => {
      document.location.reload();
    });
  }

  filtrar(cartas: any) {

  }

}
