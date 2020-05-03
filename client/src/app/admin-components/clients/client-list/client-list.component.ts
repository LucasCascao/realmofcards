/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {MockClient} from '../../../../mock/mock-cliente.model';
import {Pessoa} from '../../../../model/domain/pessoa.model';
import {ClienteService} from '../../../../services/cliente.service';
import {ResultClient} from '../../../../model/results/result-person.model';
import { UtilService } from 'src/services/util.service';
import { Pedido } from 'src/model/domain/pedido.model';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  resultClient: ResultClient;

  clients: Array<Pessoa>;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.listar();
  }

  listar() {
    console.log(this.resultClient);
    this.service.get(new Pessoa(), 'pessoas').subscribe(resultado => {
      this.clients = resultado?.entidades;
    });
  }

  getPedidoPorCliente(cliente: Pessoa): number{
    let pedidos: Array<Pedido>;
    let pedido: Pedido = new Pedido();
    pedido.cliente = cliente;
    this.service.get(pedido, 'pedidos').subscribe(resultado => {
      pedidos = resultado?.entidades;
    });
    return pedidos.length;
  }

  filtrar(cartas: any) {

  }

}
