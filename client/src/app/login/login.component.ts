import { Component, OnInit } from '@angular/core';
import {AuthService} from './auth.service';
import {Usuario} from '../../model/usuario.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public usuario: Usuario = new Usuario();

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  signIn() {
    this.authService.signIn(this.usuario);
  }

}
