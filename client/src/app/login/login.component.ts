import { Component, OnInit } from '@angular/core';
import {AuthService} from './auth.service';
import {Person} from '../../model/person.model';
import {AuthUserService} from "../../services/auth-user.service";
import {User} from "../../model/user.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public usuario: User = new User();

  constructor(private authService: AuthUserService, private router: Router) { }

  ngOnInit(): void {
  }

  signIn() {
    this.authService.autenticar(this.usuario).subscribe(resultado => {
      this.usuario = resultado.entidades[0];
      if (resultado.msg !== null) {
        this.router.navigate([ '/product-market-page']);
      } else {
        alert('Login ou senha invalido');
      }
    });
  }

}
