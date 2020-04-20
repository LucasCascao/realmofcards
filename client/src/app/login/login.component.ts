import { Component, OnInit } from '@angular/core';
import {AuthService} from './auth.service';
import {Usuario} from '../../model/domain/usuario.model';
import {Router} from '@angular/router';
import {Util} from '../shared/app.util';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public usuario: Usuario = new Usuario();

  constructor(private usuarioService: AuthService, private router: Router, private appUtil: Util) { }

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
    this.usuarioService.signIn(this.usuario);
  }

}
