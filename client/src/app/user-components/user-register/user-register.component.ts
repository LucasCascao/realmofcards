import { Component, OnInit } from '@angular/core';
import { Client } from 'src/model/client.model';
import {ClienteService} from '../../../services/cliente.service';
import {Router} from '@angular/router';
import {ResultClient} from '../../../model/result-client.model';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  client: Client = new Client();

  result: ResultClient = new ResultClient();

  confirmarSenha: string;

  constructor(private clienteService: ClienteService, private router: Router) { }

  ngOnInit(): void {
    this.result.msg = '';
    this.result.entidades = [];
  }

  cadastrar() {
    this.clienteService.addCliente(this.client, this.result).subscribe(value => this.result = value)
    if (this.result.msg != null) {
      alert(this.result.msg.split('.'));
    } else {
      this.router.navigate(['/']);
    }
  }
}
