import { Component, OnInit } from '@angular/core';
import {AuthService} from './auth.service';
import {Person} from '../../model/domain/person.model';
import {AuthUserService} from "../../services/auth-user.service";
import {User} from "../../model/domain/user.model";
import {Router} from "@angular/router";
import {UsuarioService} from "../../services/usuario.service";
import {Util} from "../shared/app.util";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public usuario: User = new User();

  constructor(private usuarioService: AuthService, private router: Router, private appUtil: Util) { }

  ngOnInit(): void {
  }

  async signIn() {
    await this.usuarioService.signIn(this.usuario);
    //   .subscribe(resultado => {
    //   this.usuario = resultado.entidades[0];
    //   console.log(resultado);
    //   if (resultado?.msg !== null) {
    //     alert(this.appUtil.getMensagensSeparadas(resultado.msg));
    //   } else {
    //     this.router.navigate([ '/product-market-page']);
    //   }
    // });
  }

}
