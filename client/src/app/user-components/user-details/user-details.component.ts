import { Component, OnInit } from '@angular/core';
import {Person} from '../../../model/domain/person.model';
import {ClienteService} from '../../../services/cliente.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  client: Person = new Person();

  constructor(private clientService: ClienteService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.client.id = this.route.snapshot.params.id;
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
