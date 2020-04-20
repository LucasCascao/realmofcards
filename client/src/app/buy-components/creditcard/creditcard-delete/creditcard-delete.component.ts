import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../../services/util.service';
import {Router} from '@angular/router';
import {CartaoCredito} from '../../../../model/domain/cartao-credito.model';
import {Pessoa} from '../../../../model/domain/pessoa.model';

@Component({
  selector: 'app-creditcard-delete',
  templateUrl: './creditcard-delete.component.html',
  styleUrls: ['./creditcard-delete.component.css']
})
export class CreditcardDeleteComponent implements OnInit {

  cartaoSelecionado: CartaoCredito;
  pessoa: Pessoa;


  constructor(private service: UtilService,
              private router: Router) { }

  ngOnInit(): void {
    this.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.cartaoSelecionado = JSON.parse(sessionStorage.getItem('cartaoSelecionado'));
  }

  deletaCartao() {
    this.service.delete(this.cartaoSelecionado.id, 'cartaocredito').subscribe(resultado => {
      this.router.navigate(['/app-logado/creditcard-list']);
    });
  }

}
