import { Component, OnInit } from '@angular/core';
import {MockClient} from '../../../../mock/mock-cliente.model';
import {MockCards} from '../../../../mock/mock-card.model';

@Component({
  selector: 'app-devolution-transit',
  templateUrl: './devolution-transit.component.html',
  styleUrls: ['./devolution-transit.component.css']
})
export class DevolutionTransitComponent implements OnInit {


  clients = new MockClient().pessoas;

  carta = new MockCards().cards[0];

  constructor() { }

  ngOnInit(): void {
  }

  filtrar(cartas: any) {

  }

}
