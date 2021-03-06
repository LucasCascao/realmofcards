import { Component, OnInit } from '@angular/core';
import {Person} from '../../../model/domain/person.model';
import { UtilService } from 'src/services/util.service';
import {Carta} from '../../../model/domain/carta.model';
import { Status } from 'src/model/domain/status.model';
import { ActivatedRoute, Routes, Router } from '@angular/router';
import {Carrinho} from '../../../model/domain/carrinho.model';
import {Item} from '../../../model/domain/item.model';
import {Util} from '../../shared/app.util';

@Component({
  selector: 'app-product-market-page',
  templateUrl: './product-market-page.component.html',
  styleUrls: ['./product-market-page.component.css']
})
export class ProductMarketPageComponent implements OnInit {

  constructor(private service: UtilService,
              private router: Router,
              private util: Util) { }

  client: Person;


  cartas: Carta[];

  carta: Carta;

  ngOnInit(): void {
    this.carta = new Carta();
    this.carta.status = new Status();
    this.carta.status.id = 1;
    this.getCartas();
  }

  getCartas() {
    this.service.get(this.carta, 'cartas')
    .subscribe( resultado => {
      this.cartas = resultado?.entidades;
    });
  }

  visualizar(id: number) {
    const carta: Carta = new Carta();
    carta.id = id;
    sessionStorage.setItem('cartaSelecionada', JSON.stringify(carta));
  }

  public adicionaItemNoCarrinho(id: number) {

    const carrinho: Carrinho = new Carrinho();

    carrinho.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));

    const item: Item = new Item();

    item.quantidade = 1;

    item.carta = new Carta();

    item.carta.id = id;

    const itens: Item[] = [];

    itens.push(item);

    carrinho.itens = itens;

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

  filtrar(cartas: any) {
  }
}
