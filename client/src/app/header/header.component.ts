import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Person} from '../../model/domain/person.model';
import {ClienteService} from '../../services/cliente.service';
import { GLOBAL } from '../shared/global.util';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  client: Person = new Person();

  constructor(private route: ActivatedRoute, private clienteService: ClienteService) { }

  ngOnInit() {
    this.client.id = GLOBAL.pessoa.usuario.id;
    this.getUser();
  }

  async getUser() {
    await this.clienteService.getClientes(this.client).subscribe(resultado => {
      this.client = GLOBAL.pessoa = resultado?.entidades[0];
    });
  }

}
