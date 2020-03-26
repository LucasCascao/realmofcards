import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UtilService } from 'src/services/util.service';
import { Carta } from 'src/model/domain/carta.model';
import { Category } from 'src/model/domain/category.model';
import {GLOBAL} from '../../../shared/global.util';

@Component({
  selector: 'app-card-alter',
  templateUrl: './card-alter.component.html',
  styleUrls: ['./card-alter.component.css']
})
export class CardAlterComponent implements OnInit {

  constructor(private route: ActivatedRoute, private serviceCarta: UtilService, private serviceCategoria: UtilService) { }

  carta: Carta = new Carta();
  categorias: Category[];

  ngOnInit(): void {
    this.carta.id = GLOBAL.carta.id;
    this.getCategorias();
  }

  async getCategorias() {


    const categoria: Category = new Category();

    await this.serviceCategoria.get(categoria, 'categorias').subscribe(resultado => {
      this.categorias = resultado?.entidades;
      this.serviceCarta.get(this.carta, 'cartas').subscribe(resultado2 => {
        this.carta = resultado2?.entidades[0];
        console.log(resultado2);
      });
    });
  }

}
