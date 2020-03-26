import { Component, OnInit } from '@angular/core';
import {MockCards} from "../../../mock/mock-card.model";
import {Person} from "../../../model/domain/person.model";
import { UtilService } from 'src/services/util.service';
import {Carta} from '../../../model/domain/carta.model';
import { Status } from 'src/model/domain/status.model';
import { GLOBAL } from 'src/app/shared/global.util';
import { ActivatedRoute, Routes, Router } from '@angular/router';

@Component({
  selector: 'app-product-market-page',
  templateUrl: './product-market-page.component.html',
  styleUrls: ['./product-market-page.component.css']
})
export class ProductMarketPageComponent implements OnInit {

  constructor(private service: UtilService, private router: Router) { }

  client: Person;


  cartas: Carta[];

  ngOnInit(): void {
    console.log(GLOBAL)
    this.client = JSON.parse(localStorage.getItem('userAutenticado'));
    this.getCartas();
  }

  async getCartas(){
    let carta: Carta = new Carta();
    carta.status = new Status();
    carta.status.id = 1;
    await this.service.get(carta, 'cartas')
    .subscribe( resultado => {
      this.cartas = resultado?.entidades;
      console.log(resultado);
    });
  }

  visualizar(id: number){
    GLOBAL.carta = new Carta;
    GLOBAL.carta.id = id;
  }

  filtrar(cartas: any) {
  }
}
