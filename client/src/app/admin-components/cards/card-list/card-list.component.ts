import { Component, OnInit } from '@angular/core';
import {Category} from '../../../../model/domain/category.model';
import {MockCategory} from '../../../../mock/mock-categories.model';
import {MockCards} from '../../../../mock/mock-card.model';
import { UtilService } from 'src/services/util.service';
import { Carta } from 'src/model/domain/carta.model';
import { Status } from 'src/model/domain/status.model';

@Component({
  selector: 'app-card-list',
  templateUrl: './card-list.component.html',
  styleUrls: ['./card-list.component.css']
})
export class CardListComponent implements OnInit {

  cartas: Carta[];

  cartasFiltradas: Carta[];

  valorBuscado: string;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.getCartasAtivas();
  }

  async getCartasAtivas(){
    const carta: Carta = new Carta();
    carta.status = new Status();
    carta.status.id = 1;

    await this.service.get(carta, 'cartas').subscribe(resultado => {
      this.cartas = resultado?.entidades;
      this.cartasFiltradas = resultado?.entidades;
    });
  }

  seleciona(id: number) {
    const carta: Carta = new Carta();
    carta.id = id;
    sessionStorage.setItem('cartaSelecionada', JSON.stringify(carta));
  }

  filtrar() {
    this.cartasFiltradas = this.cartas.filter((carta) => carta.nome.toUpperCase().startsWith(this.valorBuscado.toUpperCase()));
  }

}
