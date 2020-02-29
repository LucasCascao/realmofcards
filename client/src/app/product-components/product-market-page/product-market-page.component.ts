import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-market-page',
  templateUrl: './product-market-page.component.html',
  styleUrls: ['./product-market-page.component.css']
})
export class ProductMarketPageComponent implements OnInit {

  constructor() { }

  cartas = [
    {
      nome: 'Angel of Serenity',
      value: '15,00',
      imageUrl: '/assets/images/card_2.png',
      catJogo: 'Magic',
      catCarta: 'Monstro'
    },
    {
      nome: 'Angel of Serenity',
      value: '15,00',
      imageUrl: '/assets/images/card_2.png',
      catJogo: 'Magic',
      catCarta: 'Monstro'
    },
    {
      nome: 'Angel of Serenity',
      value: '15,00',
      imageUrl: '/assets/images/card_2.png',
      catJogo: 'Magic',
      catCarta: 'Monstro'
    },
    {
      nome: 'Angel of Serenity',
      value: '15,00',
      imageUrl: '/assets/images/card_2.png',
      catJogo: 'Magic',
      catCarta: 'Monstro'
    },
    {
      nome: 'Angel of Serenity',
      value: '15,00',
      imageUrl: '/assets/images/card_2.png',
      catJogo: 'Magic',
      catCarta: 'Monstro'
    },
    {
      nome: 'Angel of Serenity',
      value: '15,00',
      imageUrl: '/assets/images/card_2.png',
      catJogo: 'Magic',
      catCarta: 'Monstro'
    }
  ];

  ngOnInit(): void {
  }
}
