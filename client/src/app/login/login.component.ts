import { Component, OnInit } from '@angular/core';
import {AuthService} from './auth.service';
import {Client} from '../../model/client.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public usuario: Client = new Client();

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  signIn() {
    this.authService.signIn(this.usuario);
  }

}
