import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { UtilService } from 'src/services/util.service';
import { Person } from 'src/model/domain/person.model';
import { GLOBAL } from '../shared/global.util';
import { User } from 'src/model/domain/user.model';

@Component({
  selector: 'app-app-logado',
  templateUrl: './app-logado.component.html',
  styleUrls: ['./app-logado.component.css']
})
export class AppLogadoComponent implements OnInit {

  pessoa: Person;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.pessoa = new Person();
    this.pessoa.usuario = JSON.parse(localStorage.getItem('usuarioLogado'));
    // this.pessoa.id = GLOBAL.pessoa.usuario.id;
    this.pessoa.id = this.pessoa.usuario.id;
    this.getPessoaLogada();
  }

  async getPessoaLogada() {
    await this.service.get(this.pessoa, 'pessoas').subscribe( resultado => {
      GLOBAL.pessoa = resultado?.entidades[0];
    });
  }
}
