import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ClienteService} from '../../../services/cliente.service';

@Component({
  selector: 'app-user-delete',
  templateUrl: './user-delete.component.html',
  styleUrls: ['./user-delete.component.css']
})
export class UserDeleteComponent implements OnInit {

  constructor(private router: Router, private clienteService: ClienteService) { }

  ngOnInit(): void {
  }

  excluirConta() {
    // tslint:disable-next-line:radix
    this.clienteService.deleteCliente(Number.parseInt(sessionStorage.getItem('clienteLogadoId')));
    // this.router.navigate(['/']);
  }

}
