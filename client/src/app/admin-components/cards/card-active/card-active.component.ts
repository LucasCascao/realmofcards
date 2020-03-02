import { Component, OnInit } from '@angular/core';
import {MockCards} from "../../../../mock/mock-card.model";

@Component({
  selector: 'app-card-active',
  templateUrl: './card-active.component.html',
  styleUrls: ['./card-active.component.css']
})
export class CardActiveComponent implements OnInit {

  carta = new MockCards().cards[0];

  constructor() { }

  ngOnInit(): void {
  }

}
