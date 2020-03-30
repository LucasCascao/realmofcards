import {EventEmitter, Injectable} from '@angular/core';
import {Person} from '../../model/domain/person.model';
import {Router} from '@angular/router';
import {ClienteService} from '../../services/cliente.service';
import {ResultClient} from '../../model/results/result-person.model';
import {User} from '../../model/domain/user.model';
import {ResultUser} from '../../model/results/result-user.model';
import {UsuarioService} from '../../services/usuario.service';
import { GLOBAL } from '../shared/global.util';
import { UtilService } from 'src/services/util.service';
import { Util } from '../shared/app.util';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  clients: Person = new Person();
  resultado: ResultUser = new ResultUser();

  mostrarMenuEmitter = new EventEmitter<boolean>();

  usuarioAutenticado = false;


  constructor(private router: Router, private service: UtilService, private util: Util) { }

  async signIn(user: User) {

    await this.service.get(user, 'usuarios').subscribe( resuldado => {
      this.clients.usuario = resuldado?.entidades[0];
      if (resuldado.msg !== null) {
        alert(this.util.getMensagensSeparadas(resuldado.msg))
      } else {
        sessionStorage.setItem('usuarioLogado', JSON.stringify(this.clients.usuario));
        // GLOBAL.pessoa = new Person();
        // GLOBAL.pessoa.usuario = this.clients?.usuario;
        this.router.navigate(['/app-logado']);
      }
    });
  }

}
