import { Component, OnInit } from '@angular/core';
import {MockClient} from "../../../mock/mock-cliente.model";
import {MockCards} from "../../../mock/mock-card.model";

@Component({
  selector: 'app-devolution-finalization',
  templateUrl: './devolution-finalization.component.html',
  styleUrls: ['./devolution-finalization.component.css']
})
export class DevolutionFinalizationComponent implements OnInit {

  carta = new MockCards().cards[0];

  constructor() { }

  ngOnInit(): void {
  }

}
