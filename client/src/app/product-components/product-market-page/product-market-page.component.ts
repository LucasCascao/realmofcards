import { Component, OnInit } from '@angular/core';
import {Person} from '../../../model/domain/person.model';
import { UtilService } from 'src/services/util.service';
import {Carta} from '../../../model/domain/carta.model';
import { Status } from 'src/model/domain/status.model';
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
    this.carta = new Carta();
    this.carta.status = new Status();
    this.carta.status.id = 1;
    this.getCartas();
  }

  getCartas() {
    this.service.get(this.carta, 'cartas')
    .subscribe( resultado => {
      this.cartas = resultado?.entidades;
    });
  }

  visualizar(id: number) {
    const carta: Carta = new Carta();
    carta.id = id;
    sessionStorage.setItem('cartaSelecionada', JSON.stringify(carta));
  }

  filtrar(cartas: any) {
  }
}
