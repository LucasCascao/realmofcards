import { Component, OnInit } from '@angular/core';
import {MockClient} from "../../../../mock/mock-cliente.model";

@Component({
  selector: 'app-client-inactive',
  templateUrl: './client-inactive.component.html',
  styleUrls: ['./client-inactive.component.css']
})
export class ClientInactiveComponent implements OnInit {

  client = new MockClient().pessoas[0];

  constructor() { }

  ngOnInit(): void {
  }

}
