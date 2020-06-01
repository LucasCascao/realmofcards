/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {MockClient} from '../../../../mock/mock-cliente.model';
import {MockCards} from '../../../../mock/mock-card.model';
import {Pedido} from '../../../../model/domain/pedido.model';
import {UtilService} from '../../../../services/util.service';
import {StatusPedido} from '../../../../model/domain/status-pedido.model';
import {Transicao} from '../../../../model/domain/transicao.model';
import { Item } from 'src/model/domain/item.model';

@Component({
  selector: 'app-request-trade',
  templateUrl: './request-trade.component.html',
  styleUrls: ['./request-trade.component.css']
})
export class RequestTradeComponent implements OnInit {

  trocas: Array<Transicao>;

  trocasFiltrada: Array<Transicao>;

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
    const troca: Transicao = new Transicao();
    troca.pedido = pedido;
    this.service.get(troca, 'trocas').subscribe(resultado => {
      this.trocas = resultado?.entidades;
      this.trocasFiltrada = this.trocas;
    });
  }

  aprovarRequisicaoTroca(troca: Transicao){
    let status: StatusPedido = new StatusPedido();
    status.id = 9;
    troca.pedido.statusPedido = status;
    this.service.update(troca, 'trocas').subscribe((resultado) => {
      if(resultado?.msg == null){
        this.trocas.splice(this.trocas.indexOf(troca), 1);
        this.trocasFiltrada = this.trocas;
      }
    });
  }

  rejeitarRequisicaoTroca(troca: Transicao){
    let status: StatusPedido = new StatusPedido();
    status.id = 8;
    troca.pedido.statusPedido = status;
    this.service.update(troca, 'trocas').subscribe(() => {
      document.location.reload();
    });
  }

  filtrar() {
    this.trocasFiltrada = this.trocas.filter((troca) =>
      troca?.pedido?.cliente?.nome.toUpperCase().startsWith(this.valorBuscado.toUpperCase())
      || troca?.pedido?.codigoPedido.startsWith(this.valorBuscado)
      || this.contemCarta(troca?.pedido?.itemList)
      || troca?.pedido?.cliente?.usuario?.email.startsWith(this.valorBuscado)
      || troca?.codigo.startsWith(this.valorBuscado)
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
