import { Component, OnInit } from '@angular/core';
import {AuthService} from './login/auth.service';
import {Observable} from 'rxjs';
import {Client} from '../model/client.model';
import {ResultClient} from '../model/result-client.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'realmofcards';

  public mostrarMenu = false;

  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {
    this.authService.mostrarMenuEmitter.subscribe(
      mostrar => this.mostrarMenu = mostrar
    );
  }

}
