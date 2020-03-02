import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-creditcard-list',
  templateUrl: './creditcard-list.component.html',
  styleUrls: ['./creditcard-list.component.css']
})
export class CreditcardListComponent implements OnInit {

  constructor() { }

  cartoes = [
    9999,
    9999,
    9999
  ]

  ngOnInit(): void {
  }

}
