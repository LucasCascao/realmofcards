import { Component, OnInit } from '@angular/core';
import { Person } from 'src/model/domain/person.model';
import {ClienteService} from '../../../services/cliente.service';
import {Router} from '@angular/router';
import {ResultClient} from '../../../model/results/result-person.model';
import {async} from '@angular/core/testing';
import {Util} from '../../shared/app.util';
import {UsuarioService} from '../../../services/usuario.service';
import {ResultUser} from '../../../model/results/result-user.model';
import {User} from '../../../model/domain/user.model';
import {Observable} from 'rxjs';
import {TipoUsuario} from '../../../model/domain/tipo-usuario';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  user: User = new User();

  person: Person = new Person();

  resultClient: ResultClient = new ResultClient();

  msg = null;

  confirmarSenha: string;

  constructor( private clienteService: ClienteService, private router: Router, private util: Util) { }

  ngOnInit(): void {
  }

  async cadastrar() {
    this.person.usuario = this.user;
    this.person.usuario.tipoUsuario = new TipoUsuario();
    this.person.usuario.tipoUsuario.id = 2;
    this.person.usuario.status.id = 1;
    console.log(this.person);
    await this.cadastrarPessoa();
  }

  async cadastrarPessoa() {
    await this.clienteService.addCliente(this.person).subscribe(value => {
      this.resultClient = value;
      if (value.msg != null) {
        this.msg = this.msg + value.msg;
      } else {
        this.person = value?.entidades[0];
      }
      this.msg = value?.msg;
      this.alertar();
    });
  }

  alertar() {
    if (this.msg !== null) {
      alert(this.util.getMensagensSeparadas(this.msg));
      console.log(this.msg);
    } else {
      this.router.navigate(['/']);
    }
  }



}
