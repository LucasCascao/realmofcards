import { Component, OnInit } from '@angular/core';
import {MockCards} from '../../../../mock/mock-card.model';
import { Carta } from 'src/model/domain/carta.model';
import { UtilService } from 'src/services/util.service';
import { Status } from 'src/model/domain/status.model';

@Component({
  selector: 'app-card-list-inactive',
  templateUrl: './card-list-inactive.component.html',
  styleUrls: ['./card-list-inactive.component.css']
})
export class CardListInactiveComponent implements OnInit {

  cartas: Carta[];

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.getCartasAtivas();
  }

  async getCartasAtivas(){
    let carta: Carta = new Carta();
    carta.status = new Status();
    carta.status.id = 2;

    await this.service.get(carta, 'cartas').subscribe(resultado => {
      this.cartas = resultado?.entidades;
      console.log(resultado)
    });
  }

  filtrar(cartas: any) {

  }

}
