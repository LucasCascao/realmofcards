import { Component, OnInit } from '@angular/core';
import {MockCards} from '../../../mock/mock-card.model';
import { UtilService } from 'src/services/util.service';
import { Carrinho } from 'src/model/domain/carrinho.model';
import { Pessoa } from 'src/model/domain/pessoa.model';
import {Item} from '../../../model/domain/item.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  carta = new MockCards().cards[0];

  carrinho: Carrinho;

  pessoa: Pessoa;

  valorTotal: number;

  constructor(private service: UtilService,
              private router: Router) { }

  ngOnInit(): void {
    this.carrinho = new Carrinho();
    this.carrinho.pessoa = JSON.parse(sessionStorage?.getItem('pessoaLogada'));
    this.getCarrinho();
  }

  getCarrinho() {
    this.service.get(this.carrinho, 'carrinhos').subscribe(resultado => {
      this.carrinho = resultado?.entidades[resultado?.entidades.length - 1];
      this.calculaValorTotal();
    });
  }

  calculaValorTotal() {
    this.valorTotal = 0;
    this.carrinho?.itemList?.forEach(item => {
      this.valorTotal += (item?.carta?.valorVenda * item?.quantidade);
    });
  }

  incrementaValor(item: Item) {
    if (item?.quantidade < item?.carta?.quantidadeEstoque) {
      item.quantidade ++;
      item.carta.quantidadeDisponivel --;
      this.valorTotal += item?.carta?.valorVenda;
    }
  }

  decrementaValor(item: Item) {
    if (item?.quantidade > 1 ) {
      item.quantidade --;
      item.carta.quantidadeDisponivel ++;
      this.valorTotal -= item?.carta?.valorVenda;
    }
  }

  continuarComprando() {
    this.gravaCarrinho('/app-logado/product-market-page');
  }

  finalizarCompra() {
    sessionStorage.setItem('valorTotal', JSON.stringify(this.valorTotal));
    sessionStorage.setItem('carrinho', JSON.stringify(this.carrinho));
    this.gravaCarrinho('/app-logado/select-address');
  }

  async gravaCarrinho(caminho: string) {
    if (this.carrinho) {
      this.carrinho.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
      await this.service.update(this.carrinho, 'carrinhos').subscribe(() => {
        this.router.navigate([`${caminho}`]);
      });
    }
  }

  deletaCartaDoCarrinho(item: Item) {
    this.service.delete(item.id, 'itens').subscribe(() => {
      document.location.reload();
    });
  }

}
