import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../../services/util.service';
import {Bandeira} from '../../../../model/domain/bandeira.model';
import {CartaoCredito} from '../../../../model/domain/cartao-credito.model';
import {Util} from '../../../shared/app.util';
import {Router} from '@angular/router';
import * as Inputmask from 'inputmask';

@Component({
  selector: 'app-creditcard-register',
  templateUrl: './creditcard-register.component.html',
  styleUrls: ['./creditcard-register.component.css']
})
export class CreditcardRegisterComponent implements OnInit {

  constructor(private service: UtilService,
              private util: Util,
              private router: Router) { }

  bandeiras: Array<Bandeira>;

  cartaoCredito: CartaoCredito;

  ngOnInit(): void {

    Inputmask().mask(document.querySelectorAll('input'));
    this.bandeiras = new Array<Bandeira>();
    this.cartaoCredito = new CartaoCredito();
    this.cartaoCredito.pessoa = JSON.parse(sessionStorage?.getItem('pessoaLogada'));
    this.cartaoCredito.preferido = false;
    this.getBandeiras();
  }

  getBandeiras() {
    this.service.get(new Bandeira(), 'bandeiras').subscribe(resultado => {
      this.bandeiras = resultado?.entidades;
    });
  }

  cadastraCartaoCredito() {
    this.service.add(this.cartaoCredito, 'cartaocredito').subscribe(resultado => {
      if (resultado.msg != null) {
        alert(this.util.getMensagensSeparadas(resultado.msg));
      } else {
        this.router.navigate(['/app-logado/select-creditcard']);
      }
    });
  }

}
