/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {MockClient} from '../../../../mock/mock-cliente.model';
import {MockCards} from '../../../../mock/mock-card.model';
import {Pedido} from '../../../../model/domain/pedido.model';
import {UtilService} from '../../../../services/util.service';
import {StatusPedido} from '../../../../model/domain/status-pedido.model';
import {Transicao} from '../../../../model/domain/transicao.model';
import { Item } from 'src/model/domain/item.model';
import { StatusTransacao } from 'src/model/domain/status-transicao';
import { TipoTransicao } from 'src/model/domain/tipo-transicao';

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
    let statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 7;
    let pedido = new Pedido();
    pedido.statusPedido = statusPedido;
    let statusTransacao: StatusTransacao = new StatusTransacao();
    statusTransacao.id = 1;
    let tipoTransicao: TipoTransicao = new TipoTransicao();
    tipoTransicao.id = 1;
    let troca: Transicao = new Transicao();
    troca.pedido = pedido;
    troca.statusTransacao = statusTransacao;
    troca.tipoTransicao = tipoTransicao;
    this.service.get(troca, 'transicoes').subscribe(resultado => {
      this.trocas = resultado?.entidades;
      this.trocasFiltrada = this.trocas;
    });
  }

  aprovarRequisicaoTroca(troca: Transicao){
    let status: StatusPedido = new StatusPedido();
    status.id = 9;
    troca.pedido.statusPedido = status;
    this.service.update(troca, 'transicoes').subscribe((resultado) => {
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
    this.service.update(troca, 'transicoes').subscribe((resultado) => {
      if(resultado?.msg == null){
        this.trocas.splice(this.trocas.indexOf(troca), 1);
        this.trocasFiltrada = this.trocas;
      }
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
