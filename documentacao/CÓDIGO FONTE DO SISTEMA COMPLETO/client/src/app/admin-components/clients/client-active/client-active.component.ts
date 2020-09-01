import { Component, OnInit } from '@angular/core';
import {MockClient} from "../../../../mock/mock-cliente.model";

@Component({
  selector: 'app-client-active',
  templateUrl: './client-active.component.html',
  styleUrls: ['./client-active.component.css']
})
export class ClientActiveComponent implements OnInit {

  client = new MockClient().pessoas[0];

  constructor() { }

  ngOnInit(): void {
  }

}
