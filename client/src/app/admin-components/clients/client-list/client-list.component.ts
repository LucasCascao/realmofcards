import { Component, OnInit } from '@angular/core';
import {MockClient} from '../../../../mock/mock-cliente.model';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  clients = new MockClient().pessoas;

  constructor() { }

  ngOnInit(): void {
  }

  filtrar(cartas: any) {

  }

}
