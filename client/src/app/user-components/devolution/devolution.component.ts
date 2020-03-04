import { Component, OnInit } from '@angular/core';
import {MockCards} from '../../../mock/mock-card.model';
import {MockClient} from '../../../mock/mock-cliente.model';

@Component({
  selector: 'app-devolution',
  templateUrl: './devolution.component.html',
  styleUrls: ['./devolution.component.css']
})
export class DevolutionComponent implements OnInit {

  carta = new MockCards().cards[0];

  cliente = new MockClient().pessoas[0];

  constructor() { }

  ngOnInit(): void {
  }

}
