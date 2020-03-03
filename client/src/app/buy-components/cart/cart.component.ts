import { Component, OnInit } from '@angular/core';
import {MockCards} from '../../../mock/mock-card.model';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  carta = new MockCards().cards[0];

  constructor() { }

  ngOnInit(): void {
  }

}
