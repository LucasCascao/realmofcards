/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {MockClient} from '../../../../mock/mock-cliente.model';
import {MockCards} from '../../../../mock/mock-card.model';
import {Pedido} from '../../../../model/domain/pedido.model';
import {UtilService} from '../../../../services/util.service';
import {StatusPedido} from '../../../../model/domain/status-pedido.model';
import {Troca} from '../../../../model/domain/troca.model';
import { Item } from 'src/model/domain/item.model';

@Component({
  selector: 'app-request-trade',
  templateUrl: './request-trade.component.html',
  styleUrls: ['./request-trade.component.css']
})
export class RequestTradeComponent implements OnInit {

  trocas: Array<Troca>;

  trocasFiltrada: Array<Troca>;

  valorBuscado: string;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.getTrocas();
  }

  getTrocas() {
    const statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 7;
    const pedido = new Pedido();
    pedido.statusPedido = statusPedido;
    const troca: Troca = new Troca();
    troca.pedidoParaTroca = pedido;
    this.service.get(troca, 'trocas').subscribe(resultado => {
      this.trocas = resultado?.entidades;
      this.trocasFiltrada = this.trocas;
    });
  }

  aprovarRequisicaoTroca(troca: Troca){
    let status: StatusPedido = new StatusPedido();
    status.id = 9;
    troca.pedidoParaTroca.statusPedido = status;
    this.service.update(troca, 'trocas').subscribe((resultado) => {
      if(resultado?.msg == null){
        this.trocas.splice(this.trocas.indexOf(troca), 1);
        this.trocasFiltrada = this.trocas;
      }
    });
  }

  rejeitarRequisicaoTroca(troca: Troca){
    let status: StatusPedido = new StatusPedido();
    status.id = 8;
    troca.pedidoParaTroca.statusPedido = status;
    this.service.update(troca, 'trocas').subscribe(() => {
      document.location.reload();
    });
  }

  filtrar() {
    this.trocasFiltrada = this.trocas.filter((troca) =>
      troca?.pedidoParaTroca?.cliente?.nome.toUpperCase().startsWith(this.valorBuscado.toUpperCase())
      || troca?.pedidoParaTroca?.codigoPedido.startsWith(this.valorBuscado)
      || this.contemCarta(troca?.pedidoParaTroca?.itemList)
      || troca?.pedidoParaTroca?.cliente?.usuario?.email.startsWith(this.valorBuscado)
    );
  }

  contemCarta(itens: Item[]): boolean{
    let contemCarta: boolean = false;
    itens?.forEach( item => {
      if(item?.carta?.nome.startsWith(this.valorBuscado)){
        contemCarta = true;
      }
    });
    return contemCarta;
  }

}
