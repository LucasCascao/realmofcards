import { Router } from '@angular/router';
/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/model/domain/usuario.model';
import {UtilService} from '../../../services/util.service';
import { Util } from 'src/app/shared/app.util';

@Component({
  selector: 'app-user-password-alter',
  templateUrl: './user-password-alter.component.html',
  styleUrls: ['./user-password-alter.component.css']
})
export class UserPasswordAlterComponent implements OnInit {

  usuario: Usuario;

  mensagens: string[];

  constructor(private service: UtilService, private util: Util, private router: Router) { }

  ngOnInit(): void {
    this.usuario = JSON.parse(sessionStorage.getItem('pessoaLogada'))?.usuario;
  }

  alterarSenha() {
    this.mensagens = [];
    this.service.update(this.usuario, 'usuarios').subscribe( resultado => {
      if (resultado?.msg != null) {
        this.mensagens = this.util.getMensagensSeparadas2(resultado?.msg);
      } else {
        this.router.navigate(['/app-logado/product-market-page']);
      }
    });
  }

}
