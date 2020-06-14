import { UtilService } from 'src/services/util.service';
import { Cupom } from 'src/model/domain/cupom.model';
import { Component, OnInit } from '@angular/core';
import { Pessoa } from 'src/model/domain/pessoa.model';
import { Usuario } from 'src/model/domain/usuario.model';
import { Status } from 'src/model/domain/status.model';
import * as Inputmask from 'inputmask';
import { Util } from 'src/app/shared/app.util';
import { TipoCupom } from 'src/model/domain/tipo-cupom';

@Component({
  selector: 'app-ticket-value',
  templateUrl: './ticket-value.component.html',
  styleUrls: ['./ticket-value.component.css']
})
export class TicketValueComponent implements OnInit {

  cupom: Cupom;

  mensagensErro: string[];

  mensagemSucesso: string;

  constructor(private service: UtilService,
              private util: Util) { }

  ngOnInit(): void {
    Inputmask().mask(document.querySelectorAll('input'));
    this.cupom = new Cupom();
    this.cupom.pessoa = new Pessoa();
    this.cupom.pessoa.usuario = new Usuario();
  }

  gerarCupom() {
    this.mensagensErro = [];
    this.mensagemSucesso = null;
    this.cupom.status = new Status();
    this.cupom.tipoCupom = new TipoCupom();
    this.cupom.status.id = 1;
    this.cupom.tipoCupom.id = 3;
    this.service.add(this.cupom, 'cupons').subscribe(resultado => {
      if (resultado?.msg) {
        this.mensagensErro = this.util.getMensagensSeparadas2(resultado?.msg);
      } else {
        this.cupom = resultado?.entidades[0];
        this.mensagemSucesso = 'Cupom gerado com sucesso';
      }
    });
  }

}
