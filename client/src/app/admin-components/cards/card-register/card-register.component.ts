import { Component, OnInit } from '@angular/core';
import {Category} from '../../../../model/category.model';
import {MockCategory} from '../../../../mock/mock-categories.model';

@Component({
  selector: 'app-card-register',
  templateUrl: './card-register.component.html',
  styleUrls: ['./card-register.component.css']
})
export class CardRegisterComponent implements OnInit {

  constructor() { }

  categorias: Category[] = new MockCategory().categories;

  ngOnInit(): void {
  }

}
