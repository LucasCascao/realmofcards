import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { Carta } from 'src/model/domain/carta.model';
import { UtilService } from 'src/services/util.service';
import { Carrinho } from 'src/model/domain/carrinho.model';
import { Item } from 'src/model/domain/item.model';
import {Util} from '../../shared/app.util';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  constructor(private route: ActivatedRoute,
              private service: UtilService,
              private router: Router,
              private util: Util) { }

  carta: Carta = new Carta();

  quantidade: number;

  ngOnInit(): void {
    this.quantidade = 1;
    this.carta = JSON.parse(sessionStorage.getItem('cartaSelecionada'));
    this.getCarta();
  }

  getCarta() {
    this.service.get(this.carta, 'cartas').subscribe(resultado => {
      this.carta = resultado?.entidades[0];
    });
  }

  public adicionaItemNoCarrinho(cartaSelecionada: Carta) {

    const carrinho: Carrinho = new Carrinho();

    carrinho.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));

    const item: Item = new Item();

    item.quantidade = this.quantidade;

    item.carta = new Carta();

    item.carta = cartaSelecionada;

    const itens: Item[] = [];

    itens.push(item);

    carrinho.itemList = itens;

    this.enviarCarrinho(carrinho);
  }

  async enviarCarrinho( carrinho: Carrinho) {
    await this.service.add(carrinho, 'carrinhos').subscribe(resultado => {
      if (resultado.msg == null ) {
        this.router.navigate(['/app-logado/cart']);
      } else {
        alert(this.util.getMensagensSeparadas(resultado?.msg));
      }
    });
  }
}
