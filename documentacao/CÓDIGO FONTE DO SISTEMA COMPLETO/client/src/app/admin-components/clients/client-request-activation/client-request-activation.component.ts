import { Component, OnInit } from '@angular/core';
import {MockClient} from "../../../../mock/mock-cliente.model";

@Component({
  selector: 'app-client-request-activation',
  templateUrl: './client-request-activation.component.html',
  styleUrls: ['./client-request-activation.component.css']
})
export class ClientRequestActivationComponent implements OnInit {

  clients = new MockClient().pessoas;

  constructor() { }

  ngOnInit(): void {
  }

  filtrar(cartas: any) {

  }

}
