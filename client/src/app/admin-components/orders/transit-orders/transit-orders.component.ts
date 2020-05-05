/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {Pedido} from '../../../../model/domain/pedido.model';
import {UtilService} from '../../../../services/util.service';
import {StatusPedido} from '../../../../model/domain/status-pedido.model';

@Component({
  selector: 'app-transit-orders',
  templateUrl: './transit-orders.component.html',
  styleUrls: ['./transit-orders.component.css']
})
export class TransitOrdersComponent implements OnInit {

  pedidos: Array<Pedido>;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.getPedidos();
  }

  getPedidos() {
    const statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 5;
    const pedido = new Pedido();
    pedido.statusPedido = statusPedido;
    this.service.get(pedido, 'pedidos').subscribe(resultado => {
      this.pedidos = resultado?.entidades;
    });
  }

  confirmaPedidoEntrege(pedido: Pedido) {
    const statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 6;
    pedido.statusPedido = statusPedido;
    this.service.update(pedido, 'pedidos').subscribe(() => {
      document.location.reload();
    });
  }

  filtrar(cartas: any) {

  }

}
