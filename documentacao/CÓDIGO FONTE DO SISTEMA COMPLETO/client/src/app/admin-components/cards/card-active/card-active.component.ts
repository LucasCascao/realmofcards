/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {MockCards} from '../../../../mock/mock-card.model';
import {Carta} from '../../../../model/domain/carta.model';
import {UtilService} from '../../../../services/util.service';
import {Router} from '@angular/router';
import {Status} from '../../../../model/domain/status.model';
import { TransacaoStatusCarta } from 'src/model/domain/transacao-status-carta.model';

@Component({
  selector: 'app-card-active',
  templateUrl: './card-active.component.html',
  styleUrls: ['./card-active.component.css']
})
export class CardActiveComponent implements OnInit {

  transacaoStatusCarta: TransacaoStatusCarta;

  constructor(private service: UtilService,
              private router: Router) { }

  ngOnInit(): void {
    this.transacaoStatusCarta = new TransacaoStatusCarta;
    this.transacaoStatusCarta.carta = JSON.parse(sessionStorage.getItem('cartaInativaSelecionada'));
    this.transacaoStatusCarta.carta.quantidadeDisponivel = 1;
  }

  ativaCarta() {
    if (this.transacaoStatusCarta.carta.quantidadeDisponivel > 0) {
      this.transacaoStatusCarta.carta.quantidadeEstoque = this.transacaoStatusCarta.carta.quantidadeDisponivel;
      this.service.add(this.transacaoStatusCarta, '/transacaos/status/cartas').subscribe(resultado => {
        if (resultado.msg == null) {
          this.router.navigate(['/app-logado/admin-page/admin-product-inactive-list']);
        }
      });
    } else {
      alert('Valor invalido para inserir no estoque');
    }
  }

}
