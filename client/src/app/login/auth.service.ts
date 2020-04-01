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

    await this.service.get(user, 'usuarios').subscribe( async resuldado => {
      if (resuldado.msg !== null) {
        alert(this.util?.getMensagensSeparadas(resuldado?.msg))
      } else {
        sessionStorage?.setItem('usuarioLogado', JSON.stringify(await resuldado?.entidades[0]));
        this.router.navigate(['/app-logado']);
      }
    });
  }

}
