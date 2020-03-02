import { Component, OnInit } from '@angular/core';
import {Category} from '../../../../model/category.model';
import {MockCategory} from '../../../../mock/mock-categories.model';
import {MockCards} from '../../../../mock/mock-card.model';

@Component({
  selector: 'app-card-alter',
  templateUrl: './card-alter.component.html',
  styleUrls: ['./card-alter.component.css']
})
export class CardAlterComponent implements OnInit {

  constructor() { }

  categorias: Category[] = new MockCategory().categories;

  carta = new MockCards().cards[0];

  ngOnInit(): void {
  }

}
