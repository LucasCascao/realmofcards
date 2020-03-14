import { Component, OnInit } from '@angular/core';
import { Person } from 'src/model/person.model';
import {ClienteService} from '../../../services/cliente.service';
import {Router} from '@angular/router';
import {ResultClient} from '../../../model/result-person.model';
import {async} from '@angular/core/testing';
import {Util} from '../../shared/app.util';
import {UsuarioService} from '../../../services/usuario.service';
import {ResultUser} from '../../../model/result-user.model';
import {User} from '../../../model/user.model';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  user: User = new User();

  resultUser: ResultUser = new ResultUser();

  msg = null;

  confirmarSenha: string;

  constructor( private usuarioService: UsuarioService, private router: Router, private util: Util) { }

  ngOnInit(): void {
    this.user.pessoa =  new Person();
  }

  async cadastrar() {
    console.log(this.user)
    await this.cadastrarPessoa();
  }

  async cadastrarPessoa() {
    await this.usuarioService.addUser(this.user).subscribe(value => {
      this.resultUser = value;
      if (value.msg != null) {
        this.msg = this.msg + value.msg;
      } else {
        this.user = value?.entidades[0];
      }
      this.msg = value?.msg;
      this.alertar();
    });
  }

  alertar() {
    if(this.msg !== null) {
      alert(this.util.getMensagensSeparadas(this.msg));
      console.log(this.msg);
    } else {
      this.router.navigate(['/']);
    }
  }



}
