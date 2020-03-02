import { Component, OnInit } from '@angular/core';
import {MockCards} from '../../../../mock/mock-card.model';

@Component({
  selector: 'app-card-list-inactive',
  templateUrl: './card-list-inactive.component.html',
  styleUrls: ['./card-list-inactive.component.css']
})
export class CardListInactiveComponent implements OnInit {

  cartas = new MockCards().cards;

  constructor() { }

  ngOnInit(): void {
  }

  filtrar(cartas: any) {

  }

}
