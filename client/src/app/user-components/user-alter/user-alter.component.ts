import { Component, OnInit } from '@angular/core';
import {Client} from '../../../model/client.model';
import {ClienteService} from '../../../services/cliente.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-alter',
  templateUrl: './user-alter.component.html',
  styleUrls: ['./user-alter.component.css']
})
export class UserAlterComponent implements OnInit {

  client: Client = new Client();

  constructor(private clientService: ClienteService, private router: Router) { }

  ngOnInit(): void {
    this.getCliente();
  }

  getCliente() {
    // tslint:disable-next-line:radix
    this.client.id = Number.parseInt(sessionStorage.getItem('clienteLogadoId'));
    this.clientService.getClientes(this.client).subscribe( dado => this.client = dado.entidades[0]);
  }

  alterarCliente() {
    this.clientService.updateCliente(this.client);
    this.router.navigate(['/user-details']);
  }

}
