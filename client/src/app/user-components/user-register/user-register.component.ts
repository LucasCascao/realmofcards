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
import { UtilService } from 'src/services/util.service';
import { Status } from 'src/model/domain/status.model';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  user: User = new User();

  person: Person = new Person();

  msg = null;

  constructor( private service: UtilService, private router: Router, private util: Util) { }

  ngOnInit(): void {
  }

  cadastrar() {
    this.person.usuario = this.user;
    this.person.usuario.tipoUsuario = new TipoUsuario();
    this.person.usuario.tipoUsuario.id = 2;
    this.person.usuario.status = new Status();
    this.person.usuario.status.id = 1;
    this.cadastrarPessoa();
  }

  cadastrarPessoa() {
    this.service.add(this.person, 'pessoas').subscribe(resultado => {
      if (resultado.msg !== null) {
        alert(this.util.getMensagensSeparadas(resultado?.msg));
      } else {
        this.router.navigate(['/']);
      }
    });
  }
}
