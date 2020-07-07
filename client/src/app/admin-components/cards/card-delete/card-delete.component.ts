/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../../services/util.service';
import {Carta} from '../../../../model/domain/carta.model';
import {ActivatedRoute, Route, Router} from '@angular/router';
import { TransacaoStatusCarta } from 'src/model/domain/transacao-status-carta.model';
import { Status } from 'src/model/domain/status.model';

@Component({
  selector: 'app-card-delete',
  templateUrl: './card-delete.component.html',
  styleUrls: ['./card-delete.component.css']
})
export class CardDeleteComponent implements OnInit {

  constructor(private service: UtilService, private route: ActivatedRoute, private router: Router) { }

  carta: Carta;

  transacaoStatus: TransacaoStatusCarta;

  ngOnInit(): void {
    this.transacaoStatus = new TransacaoStatusCarta;
    this.transacaoStatus.carta = JSON.parse(sessionStorage.getItem('cartaSelecionada'));
    //this.getCarta();
  }

  async getCarta() {
    await this.service.get(this.carta, 'cartas').subscribe( resultado => {
      this.carta = resultado?.entidades[0];
    });
  }

  async deletaCarta() {
    this.transacaoStatus.carta.quantidadeDisponivel = 0;
    this.transacaoStatus.carta.quantidadeEstoque = 0;
    this.service.add(this.transacaoStatus, '/transacaos/status/cartas').subscribe(resultado => this.naveguePara());
  }

  naveguePara() {
    this.router.navigate(['/app-logado/admin-page']);
  }

}
