/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import { Troca } from 'src/model/domain/troca.model';
import { UtilService } from 'src/services/util.service';
import { StatusPedido } from 'src/model/domain/status-pedido.model';
import { Pedido } from 'src/model/domain/pedido.model';
import { Item } from 'src/model/domain/item.model';

@Component({
  selector: 'app-transit-trade',
  templateUrl: './transit-trade.component.html',
  styleUrls: ['./transit-trade.component.css']
})
export class TransitTradeComponent implements OnInit {

  trocas: Array<Troca>;

  trocasFiltrada: Array<Troca>;

  valorBuscado: string;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.getTrocas();
  }

  getTrocas() {
    const statusPedido: StatusPedido = new StatusPedido();
    statusPedido.id = 9;
    const pedido = new Pedido();
    pedido.statusPedido = statusPedido;
    const troca: Troca = new Troca();
    troca.pedidoParaTroca = pedido;
    this.service.get(troca, 'trocas').subscribe(resultado => {
      this.trocas = resultado?.entidades;
      this.trocasFiltrada = this.trocas;
    });
  }

  acusarRecebimentoDoProduto(troca: Troca){
    let status: StatusPedido = new StatusPedido();
    status.id = 10;
    troca.pedidoParaTroca.statusPedido = status;
    this.service.update(troca, 'trocas').subscribe((resultado) => {
      if(resultado?.msg == null){
        this.trocas.splice(this.trocas.indexOf(troca), 1);
        this.trocasFiltrada = this.trocas;
      }
    });
  }

  filtrar() {
    this.trocasFiltrada = this.trocas.filter((troca) =>
      troca?.pedidoParaTroca?.cliente?.nome.toUpperCase().startsWith(this.valorBuscado.toUpperCase())
      || troca?.pedidoParaTroca?.codigoPedido.startsWith(this.valorBuscado)
      || this.contemCarta(troca?.pedidoParaTroca?.itemList)
      || troca?.pedidoParaTroca?.cliente?.usuario?.email.startsWith(this.valorBuscado)
      || troca?.pedidoParaTroca?.dataCompra.toString().startsWith(this.valorBuscado)
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
