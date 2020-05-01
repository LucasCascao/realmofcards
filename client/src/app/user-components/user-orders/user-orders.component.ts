import { Component, OnInit } from '@angular/core';
import { UtilService } from 'src/services/util.service';
import { Pessoa } from 'src/model/domain/pessoa.model';
import { Pedido } from 'src/model/domain/pedido.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-orders',
  templateUrl: './user-orders.component.html',
  styleUrls: ['./user-orders.component.css']
})
export class UserOrdersComponent implements OnInit {

  constructor(private service: UtilService,
              private router: Router) { }

  pedidos: Array<Pedido>;

  ngOnInit(): void {
    this.getPedidos();
  }

  getPedidos() {
    const pedido = new Pedido();
    pedido.cliente = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.service.get(pedido, 'pedidos').subscribe(resultado => {
      this.pedidos = resultado?.entidades;
    });
  }

  solicitaTroca(pedido : Pedido){
    sessionStorage.setItem('pedidoSelecionado', JSON.stringify(pedido));
    this.router.navigate(['/app-logado/user-product-trade']);
  }

}
