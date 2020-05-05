/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { UtilService } from 'src/services/util.service';
import { Carta } from 'src/model/domain/carta.model';
import { Category } from 'src/model/domain/category.model';
import {Util} from '../../../shared/app.util';

@Component({
  selector: 'app-card-alter',
  templateUrl: './card-alter.component.html',
  styleUrls: ['./card-alter.component.css']
})
export class CardAlterComponent implements OnInit {

  constructor(private route: ActivatedRoute,
              private service: UtilService,
              private serviceCategoria: UtilService,
              private util: Util, private router: Router) { }

  carta: Carta = new Carta();
  categorias: Category[];
  mensagens = [];

  ngOnInit(): void {
    this.carta = JSON.parse(sessionStorage.getItem('cartaSelecionada'));
    this.getCategorias();
  }

  async getCategorias() {
    const categoria: Category = new Category();
    await this.serviceCategoria.get(categoria, 'categorias').subscribe(async resultado => {
      this.categorias = resultado?.entidades;
      await this.service.get(this.carta, 'cartas').subscribe(resultado2 => {
        this.carta = resultado2?.entidades[0];
      });
    });
  }

  async alterarCarta() {
    this.mensagens = [];
    const nomeArquivo: string[] = this.carta.imagemPath.split('\\');
    this.carta.imagemPath = nomeArquivo[nomeArquivo.length - 1];
    this.carta.quantidadeEstoque = this.carta.quantidadeDisponivel;
    await this.service.update(this.carta, 'cartas').subscribe(resultado => {
      if (resultado.msg == null) {
        this.carta = resultado?.entidades[0];
        this.router.navigate(['/app-logado/admin-page/admin-product-list']);
      } else {
        this.mensagens = this.util.getMensagensSeparadas2(resultado?.msg);
      }
    });
  }

}
