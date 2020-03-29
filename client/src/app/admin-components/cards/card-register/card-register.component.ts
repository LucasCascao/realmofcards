import { Component, OnInit } from '@angular/core';
import { Category } from '../../../../model/domain/category.model';
import { UtilService } from 'src/services/util.service';
import { Carta } from '../../../../model/domain/carta.model';

@Component({
  selector: 'app-card-register',
  templateUrl: './card-register.component.html',
  styleUrls: ['./card-register.component.css']
})
export class CardRegisterComponent implements OnInit {

  constructor(private service: UtilService) { }

  categorias: Category[];

  carta: Carta;

  ngOnInit(): void {
    this.getCategorias();
  }

  async getCategorias() {
    await this.service.get(new Category(), 'categorias').subscribe(resultado => {
      this.categorias = resultado?.entidades;
    });
  }

  cadastraCarta() {

  }

}
