/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {AuthService} from './auth.service';
import {Usuario} from '../../model/domain/usuario.model';
import {Router} from '@angular/router';
import {Util} from '../shared/app.util';
import { UtilService } from 'src/services/util.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public usuario: Usuario = new Usuario();

  mensagens: string[];

  constructor(private router: Router, private service: UtilService, private util: Util) { }

  ngOnInit(): void {
    this.estaLogado();
  }

  estaLogado() {
    const pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    if (pessoa !== null && pessoa !== undefined) {
      this.router.navigate(['/app-logado']);
    }
  }

  signIn() {
    this.mensagens = [];
    this.service.get(this.usuario, 'usuarios').subscribe( async resuldado => {
      if (resuldado.msg !== null) {
        this.mensagens = this.util.getMensagensSeparadas2(resuldado?.msg);
      } else {
        sessionStorage?.setItem('pessoaLogada', JSON.stringify(await resuldado?.entidades[0]));
        sessionStorage?.setItem('isAdmin', JSON.stringify(resuldado?.entidades[0].usuario?.isAdmin));
        this.router.navigate(['/app-logado']);
      }
    });
  }

}
