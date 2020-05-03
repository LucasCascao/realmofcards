/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import { StatusPedido } from 'src/model/domain/status-pedido.model';
import { Pedido } from 'src/model/domain/pedido.model';
import { UtilService } from 'src/services/util.service';

@Component({
  selector: 'app-orders-delivered',
  templateUrl: './orders-delivered.component.html',
  styleUrls: ['./orders-delivered.component.css']
})
export class OrdersDeliveredComponent implements OnInit {

  pedidos: Array<Pedido>;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.getPedidos();
  }

  getPedidos() {
    const statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 3;
    const pedido = new Pedido();
    pedido.statusPedido = statusPedido;
    this.service.get(pedido, 'pedidos').subscribe(resultado => {
      this.pedidos = resultado?.entidades;
    });
  }

  enviaPedidoParaTransportar(pedido: Pedido) {
    const statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 2;
    pedido.statusPedido = statusPedido;
    pedido.administrador = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.service.update(pedido, 'pedidos').subscribe(() => {
      document.location.reload();
    });
  }

  filtrar(cartas: any) {

  }

}
