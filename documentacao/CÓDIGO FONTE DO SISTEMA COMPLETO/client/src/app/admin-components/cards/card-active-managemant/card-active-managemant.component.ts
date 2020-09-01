import { Component, OnInit } from '@angular/core';
import {MockCards} from '../../../../mock/mock-card.model';

@Component({
  selector: 'app-card-active-managemant',
  templateUrl: './card-active-managemant.component.html',
  styleUrls: ['./card-active-managemant.component.css']
})
export class CardActiveManagemantComponent implements OnInit {

  cartas = new MockCards().cards;

  constructor() { }

  ngOnInit(): void {
  }

  filtrar(cartas: any) {

  }

}
