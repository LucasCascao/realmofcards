import { Component, OnInit } from '@angular/core';
import { UtilService } from 'src/services/util.service';
import { Pessoa } from 'src/model/domain/pessoa.model';
import { Pedido } from 'src/model/domain/pedido.model';

@Component({
  selector: 'app-user-orders',
  templateUrl: './user-orders.component.html',
  styleUrls: ['./user-orders.component.css']
})
export class UserOrdersComponent implements OnInit {

  constructor(private service: UtilService) { }

  pedidos: Array<Pedido>;

  ngOnInit(): void {
    this.getPedidos();
  }

  getPedidos() {
    const pedido = new Pedido();
    pedido.cliente = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.service.get(pedido, 'pedidos').subscribe(resultado => {
      this.pedidos = resultado?.entidades;
    });

  }

}
