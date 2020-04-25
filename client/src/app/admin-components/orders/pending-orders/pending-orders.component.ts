import { Component, OnInit } from '@angular/core';
import {MockClient} from '../../../../mock/mock-cliente.model';
import {MockCards} from '../../../../mock/mock-card.model';
import {MockCategory} from '../../../../mock/mock-categories.model';
import {Pedido} from '../../../../model/domain/pedido.model';
import {UtilService} from '../../../../services/util.service';
import {StatusPedido} from '../../../../model/domain/status-pedido.model';

@Component({
  selector: 'app-pending-orders',
  templateUrl: './pending-orders.component.html',
  styleUrls: ['./pending-orders.component.css']
})
export class PendingOrdersComponent implements OnInit {

  pedidos: Array<Pedido>;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.getPedidos();
  }

  getPedidos() {
    const statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 1;
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
