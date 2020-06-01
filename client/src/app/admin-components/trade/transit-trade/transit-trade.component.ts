/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import { Transicao } from 'src/model/domain/transicao.model';
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

  trocas: Array<Transicao>;

  trocasFiltrada: Array<Transicao>;

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
    const troca: Transicao = new Transicao();
    troca.pedido = pedido;
    this.service.get(troca, 'trocas').subscribe(resultado => {
      this.trocas = resultado?.entidades;
      this.trocasFiltrada = this.trocas;
    });
  }

  acusarRecebimentoDoProduto(troca: Transicao){
    let status: StatusPedido = new StatusPedido();
    status.id = 10;
    troca.pedido.statusPedido = status;
    this.service.update(troca, 'trocas').subscribe((resultado) => {
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
