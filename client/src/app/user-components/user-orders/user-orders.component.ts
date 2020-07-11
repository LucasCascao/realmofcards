/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import { UtilService } from 'src/services/util.service';
import { Pessoa } from 'src/model/domain/pessoa.model';
import { Pedido } from 'src/model/domain/pedido.model';
import {Router} from '@angular/router';
import { Util } from 'src/app/shared/app.util';

@Component({
  selector: 'app-user-orders',
  templateUrl: './user-orders.component.html',
  styleUrls: ['./user-orders.component.css']
})
export class UserOrdersComponent implements OnInit {

  constructor(private service: UtilService,
              private router: Router,
              public util: Util) { }

  pedidos: Array<Pedido>;

  ngOnInit(): void {
    this.getPedidos();
  }

  getPedidos() {
    const pedido = new Pedido();
    pedido.cliente = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.service.get(pedido, 'pedidos').subscribe(resultado => {
      console.log(resultado)
      this.pedidos = resultado?.entidades;
    });
  }

  inserePedidoSelecionadoNaSessao(pedido: Pedido){
    sessionStorage.setItem('pedidoSelecionado', JSON.stringify(pedido));
  }

  solicitaTroca(pedido : Pedido){
    this.inserePedidoSelecionadoNaSessao(pedido);
    this.router.navigate(['/app-logado/user-product-trade']);
  }

  solicitaDevolucao(pedido : Pedido){
    this.inserePedidoSelecionadoNaSessao(pedido);
    this.router.navigate(['/app-logado/user-devolution']);
  }

  temPermissaoParaDevolucao(pedido: Pedido) : boolean {
    let temPermissao: boolean = false;
    let statusPermitidos: Array<Number> = [1,3,5,6];
    if(statusPermitidos.includes( pedido?.statusPedido?.id)){
      pedido?.itemList?.forEach( item => {
        if(item?.quantidade > 0){
          temPermissao = true;
        }
      })
    }
    return temPermissao;
  }

  temPermissaoParaTroca(pedido: Pedido) : boolean {
    let temPermissao: boolean = false;
    let statusPermitidos: Array<Number> = [6];
    if(statusPermitidos.includes( pedido?.statusPedido?.id)){
      pedido?.itemList?.forEach( item => {
        if(item?.quantidade > 0){
          temPermissao = true;
        }
      })
    }
    return temPermissao;
  }

}
