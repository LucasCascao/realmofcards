/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import { Troca } from 'src/model/domain/troca.model';
import { UtilService } from 'src/services/util.service';
import { StatusPedido } from 'src/model/domain/status-pedido.model';
import { Pedido } from 'src/model/domain/pedido.model';

@Component({
  selector: 'app-transit-trade',
  templateUrl: './transit-trade.component.html',
  styleUrls: ['./transit-trade.component.css']
})
export class TransitTradeComponent implements OnInit {

  trocas: Array<Troca>;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.getTrocas();
  }

  getTrocas() {
    const statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 9;
    const pedido = new Pedido();
    pedido.statusPedido = statusPedido;
    const troca: Troca = new Troca();
    troca.pedidoParaTroca = pedido;
    this.service.get(troca, 'trocas').subscribe(resultado => {
      this.trocas = resultado?.entidades;
    });
  }

  acusarRecebimentoDoProduto(troca: Troca){
    let status: StatusPedido = new StatusPedido();
    status.id = 10;
    troca.pedidoParaTroca.statusPedido = status;
    this.service.update(troca, 'trocas').subscribe(() => {
      document.location.reload();
    });
  }

  filtrar(cartas: any) {

  }
}
