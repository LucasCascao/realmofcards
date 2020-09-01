import { Component, OnInit } from '@angular/core';
import {MockCards} from '../../../mock/mock-card.model';
import {UtilService} from '../../../services/util.service';
import {Pedido} from '../../../model/domain/pedido.model';

@Component({
  selector: 'app-product-generate-trade-code',
  templateUrl: './product-generate-trade-code.component.html'
})
export class ProductGenerateTradeCodeComponent implements OnInit {

  carta = new MockCards().cards[0];

  pedido: Pedido;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.pedido = JSON.parse(sessionStorage.getItem('pedidoSelecionado'));
  }

}
