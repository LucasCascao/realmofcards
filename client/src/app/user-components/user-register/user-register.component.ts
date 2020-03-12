import { Component, OnInit } from '@angular/core';
import { Person } from 'src/model/person.model';
import {ClienteService} from '../../../services/cliente.service';
import {Router} from '@angular/router';
import {ResultClient} from '../../../model/result-client.model';
import {async} from '@angular/core/testing';
import {Util} from '../../shared/app.util';
import swal from 'sweetalert2';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  client: Person = new Person();

  result: ResultClient = new ResultClient();

  confirmarSenha: string;

  constructor(private clienteService: ClienteService, private router: Router, private util: Util) { }

  ngOnInit(): void {
    this.result.msg = '';
    this.result.entidades = [];
  }

  async cadastrar() {
    await this.clienteService.addCliente(this.client).subscribe(value => {
      this.result = value;
      this.alertar();
    });
  }

  alertar() {
    if (this.result.msg !== undefined || this.result.msg !== null) { alert(this.util.getMensagensSeparadas(this.result.msg)); } else {
      this.router.navigate(['/']);
    }
  }



}
