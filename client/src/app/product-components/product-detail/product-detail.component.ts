import { Component, OnInit } from '@angular/core';
import {MockCards} from "../../../mock/mock-card.model";
import {ActivatedRoute, Router} from '@angular/router';
import { Carta } from 'src/model/domain/carta.model';
import { UtilService } from 'src/services/util.service';
import { async } from '@angular/core/testing';
import { GLOBAL } from 'src/app/shared/global.util';
import { Carrinho } from 'src/model/domain/carrinho.model';
import { Item } from 'src/model/domain/item.model';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  constructor(private route: ActivatedRoute, private service: UtilService, private router: Router) { }

  carta: Carta = new Carta();

  quantidade: number;

  ngOnInit(): void {
    this.quantidade = 1;
    this.carta.id = Number.parseInt(sessionStorage.getItem('idCartaSelecionada'));
    this.getCarta();
  }

  getCarta(){
    this.service.get(this.carta, 'cartas').subscribe(resultado => {
      this.carta = resultado?.entidades[0];
    });
  }

  public adicionaItemNoCarrinho(id: number) {

    const carrinho: Carrinho = new Carrinho();

    carrinho.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));

    const item: Item = new Item();

    item.quantidade = this.quantidade;

    item.carta = new Carta();

    item.carta.id = id;

    const itens: Item[] = [];

    itens.push(item);

    carrinho.itens = itens;

    this.enviarCarrinho(carrinho);

    this.router.navigate(['/app-logado/cart']);
  }

  enviarCarrinho( carrinho: Carrinho){
    this.service.add(carrinho, 'carrinhos').subscribe();
  }
}
