import { Component, OnInit } from '@angular/core';
import {MockClient} from '../../../../mock/mock-cliente.model';
import {Client} from '../../../../model/client.model';
import {ClienteService} from '../../../../services/cliente.service';
import {ResultClient} from '../../../../model/result-client.model';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  resultClient: ResultClient;

  clients: Array<Client>;

  constructor(private clienteService: ClienteService) { }

  ngOnInit(): void {
    this.listar();
  }

  listar() {
    console.log(this.resultClient);
    this.clienteService.getClientes(new Client()).subscribe( dados => {
      this.clients = dados.entidades;
    });
  }

  filtrar(cartas: any) {

  }

}
