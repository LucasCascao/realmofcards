import { Component, OnInit } from '@angular/core';
import {MockCards} from "../../../mock/mock-card.model";
import {Person} from "../../../model/domain/person.model";
import { UtilService } from 'src/services/util.service';
import {Carta} from '../../../model/domain/carta.model';

@Component({
  selector: 'app-product-market-page',
  templateUrl: './product-market-page.component.html',
  styleUrls: ['./product-market-page.component.css']
})
export class ProductMarketPageComponent implements OnInit {

  constructor(private service: UtilService) { }

  client: Person;


  cartas: Carta[];

  ngOnInit(): void {
    this.client = JSON.parse(localStorage.getItem('userAutenticado'));
    console.log(this.client);
    this.getCartas();
  }

  async getCartas(){
    await this.service.get(new Carta, 'cartas')
    .subscribe( resultado => {
      this.cartas = resultado?.entidades;
      console.log(resultado);
    });
  }

  filtrar(cartas: any) {
  }
}
