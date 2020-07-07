/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import { UtilService } from 'src/services/util.service';
import { Carta } from 'src/model/domain/carta.model';
import { Status } from 'src/model/domain/status.model';
import { Util } from 'src/app/shared/app.util';
import { Router } from '@angular/router';

@Component({
  selector: 'app-card-list',
  templateUrl: './card-list.component.html',
  styleUrls: ['./card-list.component.css']
})
export class CardListComponent implements OnInit {

  cartas: Carta[];

  cartasFiltradas: Carta[];

  valorBuscado: string;

  constructor(private service: UtilService, public util: Util, private router: Router) { }

  ngOnInit(): void {
    this.getCartasAtivas();
  }

  async getCartasAtivas() {
    const carta: Carta = new Carta();
    carta.status = new Status();
    carta.status.id = 1;

    await this.service.get(carta, 'cartas').subscribe(resultado => {
      this.cartas = resultado?.entidades;
      this.cartasFiltradas = resultado?.entidades;
    });
  }

  altera(carta: Carta){
    this.seleciona(carta);
    this.router.navigate(['/app-logado/admin-page/admin-product-alter']);
  }

  inativa(carta: Carta){
    this.seleciona(carta);
    this.router.navigate(['/app-logado/admin-page/admin-product-delete']);
  }

  seleciona(carta: Carta) {
    sessionStorage.setItem('cartaSelecionada', JSON.stringify(carta));
  }


  filtrar() {
    this.cartasFiltradas = this.cartas.filter((carta) =>
      carta?.nome?.toUpperCase().startsWith(this.valorBuscado.toUpperCase())
      || carta?.codigo?.startsWith(this.valorBuscado)
    );
  }
}
