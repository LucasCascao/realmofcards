import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ClienteService} from '../../../services/cliente.service';
import {Client} from '../../../model/client.model';

@Component({
  selector: 'app-user-delete',
  templateUrl: './user-delete.component.html',
  styleUrls: ['./user-delete.component.css']
})
export class UserDeleteComponent implements OnInit {

  clientName = sessionStorage.getItem('clienteLogadoNome');

  constructor(private router: Router, private clienteService: ClienteService) { }

  ngOnInit(): void {
  }

  excluirConta() {

    const client = new Client();

    // tslint:disable-next-line:radix
    client.id = Number.parseInt(sessionStorage.getItem('clienteLogadoId'));

    this.clienteService.deleteCliente(client);
    this.router.navigate(['/']);
  }

}
