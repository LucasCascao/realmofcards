import { Component, OnInit } from '@angular/core';
import {Person} from '../../../model/person.model';
import {ClienteService} from '../../../services/cliente.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  client: Person = new Person();

  constructor(private clientService: ClienteService) { }

  ngOnInit(): void {
    this.getCliente();
    // this.client = JSON.parse(localStorage.getItem('userAutenticado'))[0];
    // console.log(this.client);
  }

  getCliente() {
    //tslint:disable-next-line:radix
    this.client.id = Number.parseInt(sessionStorage.getItem('clienteLogadoId'));
    this.clientService.getClientes(this.client).subscribe( dado => this.client = dado.entidades[0]);
  }

}
