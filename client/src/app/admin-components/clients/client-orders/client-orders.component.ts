import { Component, OnInit } from '@angular/core';
import { Pedido } from 'src/model/domain/pedido.model';
import { UtilService } from 'src/services/util.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-client-orders',
  templateUrl: './client-orders.component.html',
  styleUrls: ['./client-orders.component.css']
})
export class ClientOrdersComponent implements OnInit {

  constructor(private service: UtilService,
              private router: Router) { }

  pedidos: Array<Pedido>;

  ngOnInit(): void {
    this.getPedidos();
  }

  getPedidos() {
    const pedido = new Pedido();
    pedido.cliente = JSON.parse(sessionStorage.getItem('clienteEscolhido'));
    this.service.get(pedido, 'pedidos').subscribe(resultado => {
      console.log(resultado)
      this.pedidos = resultado?.entidades;
    });
  }

  solicitaTroca(pedido : Pedido){
    sessionStorage.setItem('pedidoSelecionado', JSON.stringify(pedido));
    this.router.navigate(['/app-logado/user-product-trade']);
  }

  temPermissaoParaTroca(pedido: Pedido) : boolean {
    let temPermissao: boolean = false;
    if(pedido?.statusPedido?.id === 6){
      pedido?.itemList?.forEach( item => {
        if(item?.quantidade > 0){
          temPermissao = true;
        }
      })
    }
    return temPermissao;
  }
}
