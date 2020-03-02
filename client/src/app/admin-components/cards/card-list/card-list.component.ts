import { Component, OnInit } from '@angular/core';
import {Category} from '../../../../model/category.model';
import {MockCategory} from '../../../../mock/mock-categories.model';
import {MockCards} from '../../../../mock/mock-card.model';

@Component({
  selector: 'app-card-list',
  templateUrl: './card-list.component.html',
  styleUrls: ['./card-list.component.css']
})
export class CardListComponent implements OnInit {

  categorias: Category[] = new MockCategory().categories;

  cartas = new MockCards().cards;

  constructor() { }

  ngOnInit(): void {
  }

  filtrar(cartas: any) {

  }

}
