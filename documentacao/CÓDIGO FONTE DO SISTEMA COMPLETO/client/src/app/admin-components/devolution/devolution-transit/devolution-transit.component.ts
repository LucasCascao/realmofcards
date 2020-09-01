import { Component, OnInit } from '@angular/core';
import {MockClient} from '../../../../mock/mock-cliente.model';
import {MockCards} from '../../../../mock/mock-card.model';
import { Transicao } from 'src/model/domain/transicao.model';
import { UtilService } from 'src/services/util.service';
import { StatusPedido } from 'src/model/domain/status-pedido.model';
import { Pedido } from 'src/model/domain/pedido.model';
import { StatusTransacao } from 'src/model/domain/status-transicao';
import { Item } from 'src/model/domain/item.model';
import { TipoTransicao } from 'src/model/domain/tipo-transicao';

@Component({
  selector: 'app-devolution-transit',
  templateUrl: './devolution-transit.component.html',
  styleUrls: ['./devolution-transit.component.css']
})
export class DevolutionTransitComponent implements OnInit {


  devolucoes: Array<Transicao>;

  devolucoesFiltrada: Array<Transicao>;

  valorBuscado: string;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.getTrocas();
  }

  getTrocas() {
    let statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 9;
    let pedido = new Pedido();
    pedido.statusPedido = statusPedido;
    let statusTransacao: StatusTransacao = new StatusTransacao();
    statusTransacao.id = 1
    let tipoTransicao: TipoTransicao = new TipoTransicao();
    tipoTransicao.id = 2;
    let devolucao: Transicao = new Transicao();
    devolucao.pedido = pedido;
    devolucao.statusTransacao = statusTransacao;
    devolucao.tipoTransicao = tipoTransicao;
    this.service.get(devolucao, 'transicoes').subscribe(resultado => {
      this.devolucoes = resultado?.entidades;
      this.devolucoesFiltrada = this.devolucoes;
    });
  }

  confirmarEntraga(devolucao: Transicao){
    let status: StatusPedido = new StatusPedido();
    status.id = 13;
    devolucao.pedido.statusPedido = status;
    this.service.update(devolucao, 'transicoes').subscribe((resultado) => {
      if(resultado?.msg == null){
        this.devolucoes.splice(this.devolucoes.indexOf(devolucao), 1);
        this.devolucoesFiltrada = this.devolucoesFiltrada;
      }
    });
  }

  filtrar() {
    this.devolucoesFiltrada = this.devolucoes.filter((devolucao) =>
      devolucao?.pedido?.cliente?.nome.toUpperCase().startsWith(this.valorBuscado.toUpperCase())
      || devolucao?.pedido?.codigoPedido.startsWith(this.valorBuscado)
      || this.contemCarta(devolucao?.pedido?.itemList)
      || devolucao?.pedido?.cliente?.usuario?.email.startsWith(this.valorBuscado)
      || devolucao?.pedido?.dataCompra.toString().startsWith(this.valorBuscado)
      || devolucao?.codigo.startsWith(this.valorBuscado)
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
