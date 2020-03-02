import { Component, OnInit } from '@angular/core';
import {MockCards} from "../../../../mock/mock-card.model";

@Component({
  selector: 'app-card-delete',
  templateUrl: './card-delete.component.html',
  styleUrls: ['./card-delete.component.css']
})
export class CardDeleteComponent implements OnInit {

  constructor() { }

  carta = new MockCards().cards[0];

  ngOnInit(): void {
  }

}
