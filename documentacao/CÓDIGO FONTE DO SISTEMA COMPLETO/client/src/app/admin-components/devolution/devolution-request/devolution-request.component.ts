import { Component, OnInit } from '@angular/core';
import {MockCards} from "../../../../mock/mock-card.model";
import {MockClient} from "../../../../mock/mock-cliente.model";
import { Transicao } from 'src/model/domain/transicao.model';
import { UtilService } from 'src/services/util.service';
import { StatusPedido } from 'src/model/domain/status-pedido.model';
import { Pedido } from 'src/model/domain/pedido.model';
import { Item } from 'src/model/domain/item.model';
import { StatusTransacao } from 'src/model/domain/status-transicao';
import { TipoTransicao } from 'src/model/domain/tipo-transicao';

@Component({
  selector: 'app-devolution-request',
  templateUrl: './devolution-request.component.html',
  styleUrls: ['./devolution-request.component.css']
})
export class DevolutionRequestComponent implements OnInit {

  devolucoes: Array<Transicao>;

  devolucoesFiltrada: Array<Transicao>;

  valorBuscado: string;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.getTrocas();
  }

  getTrocas() {
    let statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 11;
    let pedido = new Pedido();
    pedido.statusPedido = statusPedido;
    let statusTransacao: StatusTransacao = new StatusTransacao();
    statusTransacao.id = 1;
    let tipoTransicao: TipoTransicao = new TipoTransicao();
    tipoTransicao.id = 2;
    let devolucao: Transicao = new Transicao();
    devolucao.pedido = pedido;
    devolucao.statusTransacao = statusTransacao;
    devolucao.tipoTransicao = tipoTransicao;
    this.service.get(devolucao, 'transicoes').subscribe(resultado => {
      this.devolucoes = resultado?.entidades;
      this.devolucoesFiltrada = this.devolucoes;
      console.log(this.devolucoes);
    });
  }

  aprovarRequisicaoDevolucao(devolucao: Transicao){
    let status: StatusPedido = new StatusPedido();
    if(devolucao?.pedido?.rastreio != null){
      status.id = 9;
    } else {
      status.id = 13;
    }
    devolucao.pedido.statusPedido = status;
    this.service.update(devolucao, 'transicoes').subscribe((resultado) => {
      if(resultado?.msg == null){
        this.devolucoes.splice(this.devolucoes.indexOf(devolucao), 1);
        this.devolucoesFiltrada = this.devolucoesFiltrada;
      }
    });
  }

  rejeitarRequisicaoDevolucao(devolucao: Transicao){
    let status: StatusPedido = new StatusPedido();
    status.id = 12;
    devolucao.pedido.statusPedido = status;
    this.service.update(devolucao, 'transicoes').subscribe(resultado => {
      if(resultado?.msg == null){
        this.devolucoes.splice(this.devolucoes.indexOf(devolucao), 1);
        this.devolucoesFiltrada = this.devolucoesFiltrada;
      }
    });
  }

  filtrar() {
    this.devolucoesFiltrada = this.devolucoes.filter((troca) =>
      troca?.pedido?.cliente?.nome.toUpperCase().startsWith(this.valorBuscado.toUpperCase())
      || troca?.pedido?.codigoPedido.startsWith(this.valorBuscado)
      || this.contemCarta(troca?.pedido?.itemList)
      || troca?.pedido?.cliente?.usuario?.email.startsWith(this.valorBuscado)
      || troca?.pedido?.dataCompra.toString().startsWith(this.valorBuscado)
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
