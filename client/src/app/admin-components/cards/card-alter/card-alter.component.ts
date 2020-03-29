import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { UtilService } from 'src/services/util.service';
import { Carta } from 'src/model/domain/carta.model';
import { Category } from 'src/model/domain/category.model';
import {GLOBAL} from '../../../shared/global.util';
import {Util} from "../../../shared/app.util";

@Component({
  selector: 'app-card-alter',
  templateUrl: './card-alter.component.html',
  styleUrls: ['./card-alter.component.css']
})
export class CardAlterComponent implements OnInit {

  constructor(private route: ActivatedRoute, private service: UtilService, private serviceCategoria: UtilService, private util: Util, private router: Router) { }

  carta: Carta = new Carta();
  categorias: Category[];

  ngOnInit(): void {
    this.carta.id = GLOBAL.carta.id;
    this.getCategorias();
  }

  async getCategorias() {
    const categoria: Category = new Category();
    await this.serviceCategoria.get(categoria, 'categorias').subscribe(async resultado => {
      this.categorias = resultado?.entidades;
      await this.service.get(this.carta, 'cartas').subscribe(resultado2 => {
        this.carta = resultado2?.entidades[0];
        console.log(resultado2);
      });
    });
  }

  async alterarCarta() {
    await this.service.update(this.carta, 'cartas').subscribe(resultado => {
      if(resultado == null){
        this.carta = resultado?.entidades[0];
        this.router.navigate(['/app-logado/admin-product-list']);
      } else {
        alert(this.util.getMensagensSeparadas(resultado?.msg));
      }
    });
  }

}
