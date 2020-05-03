/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {MockCards} from '../../../../mock/mock-card.model';
import { Carta } from 'src/model/domain/carta.model';
import { UtilService } from 'src/services/util.service';
import { Status } from 'src/model/domain/status.model';
import {Router} from "@angular/router";

@Component({
  selector: 'app-card-list-inactive',
  templateUrl: './card-list-inactive.component.html',
  styleUrls: ['./card-list-inactive.component.css']
})
export class CardListInactiveComponent implements OnInit {

  cartas: Carta[];

  constructor(private service: UtilService,
              private router: Router) { }

  ngOnInit(): void {
    this.getCartasInativas();
  }

  async getCartasInativas() {
    const carta: Carta = new Carta();
    carta.status = new Status();
    carta.status.id = 2;

    await this.service.get(carta, 'cartas').subscribe(resultado => {
      this.cartas = resultado?.entidades;
      console.log(resultado);
    });
  }

  selecionaCarta(carta: Carta) {
    sessionStorage.setItem('cartaInativaSelecionada', JSON.stringify(carta));
    this.router.navigate(['/app-logado/admin-page/admin-product-active']);
  }

  filtrar(cartas: any) {

  }

}
