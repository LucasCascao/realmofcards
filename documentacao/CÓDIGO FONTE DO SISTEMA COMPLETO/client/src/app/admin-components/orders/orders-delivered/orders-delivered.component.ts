/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import { StatusPedido } from 'src/model/domain/status-pedido.model';
import { Pedido } from 'src/model/domain/pedido.model';
import { UtilService } from 'src/services/util.service';
import { Item } from 'src/model/domain/item.model';

@Component({
  selector: 'app-orders-delivered',
  templateUrl: './orders-delivered.component.html',
  styleUrls: ['./orders-delivered.component.css']
})
export class OrdersDeliveredComponent implements OnInit {

  pedidos: Array<Pedido>;

  pedidosFiltrados: Array<Pedido>;

  valorBuscado: string;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.getPedidos();
  }

  getPedidos() {
    const statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 6;
    const pedido = new Pedido();
    pedido.statusPedido = statusPedido;
    this.service.get(pedido, 'pedidos').subscribe(resultado => {
      this.pedidos = resultado?.entidades;
      this.pedidosFiltrados = this.pedidos;
    });
  }

  filtrar() {
    this.pedidosFiltrados = this.pedidos.filter((pedido) =>
      pedido.cliente?.nome.toUpperCase().startsWith(this.valorBuscado.toUpperCase())
      || pedido?.codigoPedido.startsWith(this.valorBuscado)
      || this.contemCarta(pedido?.itemList)
      || pedido?.cliente?.usuario?.email.startsWith(this.valorBuscado)
    );
  }

  contemCarta(itens: Item[]): boolean{
    let contemCarta: boolean = false;
    itens?.forEach( item => {
      if(item?.carta?.nome.startsWith(this.valorBuscado)){
        contemCarta = true;
      }
    });
    return contemCarta;
  }
}
