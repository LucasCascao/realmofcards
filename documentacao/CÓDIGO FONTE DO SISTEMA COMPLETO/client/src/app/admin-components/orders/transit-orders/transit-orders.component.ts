/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {Pedido} from '../../../../model/domain/pedido.model';
import {UtilService} from '../../../../services/util.service';
import {StatusPedido} from '../../../../model/domain/status-pedido.model';
import { Item } from 'src/model/domain/item.model';

@Component({
  selector: 'app-transit-orders',
  templateUrl: './transit-orders.component.html',
  styleUrls: ['./transit-orders.component.css']
})
export class TransitOrdersComponent implements OnInit {

  pedidos: Array<Pedido>;

  pedidosFiltrados: Array<Pedido>;

  valorBuscado: string;

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
      this.pedidosFiltrados = this.pedidos;
    });
  }

  confirmaPedidoEntrege(pedido: Pedido) {
    const statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 6;
    pedido.statusPedido = statusPedido;
    this.service.update(pedido, 'pedidos').subscribe((resultado) => {
      if(resultado?.msg == null){
        this.pedidos.splice(this.pedidos.indexOf(pedido), 1);
        this.pedidosFiltrados = this.pedidos;
      }
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
