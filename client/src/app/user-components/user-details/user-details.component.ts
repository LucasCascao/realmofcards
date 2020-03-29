import { Component, OnInit } from '@angular/core';
import {Person} from '../../../model/domain/person.model';
import {ClienteService} from '../../../services/cliente.service';
import {ActivatedRoute} from '@angular/router';
import { GLOBAL } from 'src/app/shared/global.util';
import {UtilService} from '../../../services/util.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  client: Person = new Person();

  constructor(private clientService: ClienteService, private route: ActivatedRoute, private service: UtilService) { }

  ngOnInit(): void {
    this.client.id = GLOBAL.pessoa.usuario.id;
    this.getCliente();
    // this.client = JSON.parse(localStorage.getItem('userAutenticado'))[0];
    // console.log(this.client);
  }

  async getCliente() {
    await this.clientService.getClientes(this.client).subscribe( dado => {
      this.client = dado.entidades[0];
    });
  }

}
