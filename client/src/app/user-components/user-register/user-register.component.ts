import { Component, OnInit } from '@angular/core';
import { Client } from 'src/model/client.model';
import {ClienteService} from '../../../services/cliente.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  client: Client = new Client();

  constructor(private clienteService: ClienteService, private router: Router) { }

  ngOnInit(): void {
  }

  cadastrar() {
    this.clienteService.addCliente(this.client).subscribe(dado => this.client = dado);
    this.router.navigate(['/']);
  }

}
