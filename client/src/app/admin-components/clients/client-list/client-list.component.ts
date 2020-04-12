import { Component, OnInit } from '@angular/core';
import {MockClient} from '../../../../mock/mock-cliente.model';
import {Pessoa} from '../../../../model/domain/person.model';
import {ClienteService} from '../../../../services/cliente.service';
import {ResultClient} from '../../../../model/results/result-person.model';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  resultClient: ResultClient;

  clients: Array<Pessoa>;

  constructor(private clienteService: ClienteService) { }

  ngOnInit(): void {
    this.listar();
  }

  listar() {
    console.log(this.resultClient);
    this.clienteService.getClientes(new Pessoa()).subscribe(dados => {
      this.clients = dados.entidades;
    });
  }

  filtrar(cartas: any) {

  }

}
