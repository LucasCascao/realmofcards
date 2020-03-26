import {EventEmitter, Injectable} from '@angular/core';
import {Person} from '../../model/domain/person.model';
import {Router} from '@angular/router';
import {ClienteService} from '../../services/cliente.service';
import {ResultClient} from '../../model/results/result-person.model';
import {User} from '../../model/domain/user.model';
import {ResultUser} from '../../model/results/result-user.model';
import {UsuarioService} from '../../services/usuario.service';
import { GLOBAL } from '../shared/global.util';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  clients: Person = new Person();
  resultado: ResultUser = new ResultUser();

  mostrarMenuEmitter = new EventEmitter<boolean>();

  usuarioAutenticado = false;


  constructor(private router: Router, private usuarioService: UsuarioService) { }

  async signIn(user: User) {

    await this.usuarioService.getUsers(user).subscribe(dado => {
      this.resultado = dado;
      this.clients.usuario = dado.entidades[0];

      if (dado.msg === null) {
        sessionStorage.setItem('userId', String(this.clients.id));
        GLOBAL.pessoa = new Person();
        GLOBAL.pessoa.usuario = this.clients.usuario;
        this.usuarioAutenticado = true;
        this.mostrarMenuEmitter.emit(true);
        this.router.navigate(['/app-logado']);
      } else {
        alert('Login ou senha invalida');
      }
    });
  }

}
