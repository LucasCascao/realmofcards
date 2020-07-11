/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {MockClient} from '../../../../mock/mock-cliente.model';
import {Pessoa} from '../../../../model/domain/pessoa.model';
import {ClienteService} from '../../../../services/cliente.service';
import {ResultClient} from '../../../../model/results/result-person.model';
import { UtilService } from 'src/services/util.service';
import { Pedido } from 'src/model/domain/pedido.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  resultClient: ResultClient;

  clients: Array<Pessoa>;

  clientsFiltrados: Array<Pessoa>;

  valorBuscado: string;

  constructor(private service: UtilService,
              private router: Router) { }

  ngOnInit(): void {
    this.listar();
  }

  listar() {
    console.log(this.resultClient);
    this.service.get(new Pessoa(), 'pessoas').subscribe(resultado => {
      this.clients = resultado?.entidades;
      this.clientsFiltrados = resultado?.entidades;
    });
  }

  async getPedidoPorCliente(cliente: Pessoa){
    let pedidos: [];
    let pedido: Pedido = new Pedido();
    pedido.cliente = cliente;
    await this.service.get(pedido, 'pedidos').subscribe(resultado => {
      pedidos = resultado?.entidades;
    });
    return pedidos.length;
  }

  filtrar() {
    this.clientsFiltrados = this.clients.filter((client) =>
      client.nome.toUpperCase().startsWith(this.valorBuscado.toUpperCase()) ||
      client.usuario.email.toUpperCase().startsWith(this.valorBuscado.toUpperCase()) ||
      client.usuario.codigo.toUpperCase().startsWith(this.valorBuscado.toUpperCase())
    );
  }

  verPedidosDoCliente(cliente: Pessoa){
    sessionStorage.setItem('clienteEscolhido', JSON.stringify(cliente));
    this.router.navigate(['/app-logado/admin-page/client-orders']);
  }

}
