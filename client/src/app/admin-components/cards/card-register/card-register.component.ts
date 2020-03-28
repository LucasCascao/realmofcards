import { Component, OnInit } from '@angular/core';
import {Category} from '../../../../model/domain/category.model';
import {MockCategory} from '../../../../mock/mock-categories.model';
import { UtilService } from 'src/services/util.service';

@Component({
  selector: 'app-card-register',
  templateUrl: './card-register.component.html',
  styleUrls: ['./card-register.component.css']
})
export class CardRegisterComponent implements OnInit {

  constructor(private service: UtilService) { }

  categorias: Category[];

  

  ngOnInit(): void {
    this.getCategorias();
  }

  async getCategorias(){
    await this.service.get(new Category, 'categorias').subscribe(resultado => {
      this.categorias = resultado?.entidades;
    })
  }

  // cadastra(){
  //   this.
  // }

}
