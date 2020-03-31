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

  carta: Carta;

  ngOnInit(): void {
    console.log(JSON.parse(sessionStorage.getItem('pessoaLogada')));
    this.carta = new Carta();
    this.carta.status = new Status();
    this.carta.status.id = 1;
    this.getCartas();
  }

  getCartas(){
    this.service.get(this.carta, 'cartas')
    .subscribe( resultado => {
      this.cartas = resultado?.entidades;
    });
  }

  visualizar(id: number){
    GLOBAL.carta = new Carta();
    GLOBAL.carta.id = id;
  }

  filtrar(cartas: any) {
  }
}
