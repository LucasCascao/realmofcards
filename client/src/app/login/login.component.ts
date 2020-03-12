import { Component, OnInit } from '@angular/core';
import {AuthService} from './auth.service';
import {Person} from '../../model/person.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public usuario: Person = new Person();

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  signIn() {
    this.authService.signIn(this.usuario);
  }

}
