import { Component, OnInit } from '@angular/core';
import {MockClient} from '../../../../mock/mock-cliente.model';

@Component({
  selector: 'app-client-inactive-list',
  templateUrl: './client-inactive-list.component.html',
  styleUrls: ['./client-inactive-list.component.css']
})
export class ClientInactiveListComponent implements OnInit {

  clients = new MockClient().pessoas;

  constructor() { }

  ngOnInit(): void {
  }

  filtrar(cartas: any) {

  }
}
