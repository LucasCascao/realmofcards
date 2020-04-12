import {EventEmitter, Injectable} from '@angular/core';
import {Pessoa} from '../../model/domain/person.model';
import {Router} from '@angular/router';
import {ClienteService} from '../../services/cliente.service';
import {ResultClient} from '../../model/results/result-person.model';
import {Usuario} from '../../model/domain/user.model';
import {ResultUser} from '../../model/results/result-user.model';
import {UsuarioService} from '../../services/usuario.service';
import { UtilService } from 'src/services/util.service';
import { Util } from '../shared/app.util';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  clients: Pessoa = new Pessoa();
  resultado: ResultUser = new ResultUser();

  mostrarMenuEmitter = new EventEmitter<boolean>();

  constructor(private router: Router, private service: UtilService, private util: Util) { }

  async signIn(user: Usuario) {

    await this.service.get(user, 'usuarios').subscribe( async resuldado => {
      if (resuldado.msg !== null) {
        alert(this.util?.getMensagensSeparadas(resuldado?.msg));
      } else {
        sessionStorage?.setItem('pessoaLogada', JSON.stringify(await resuldado?.entidades[0]));
        sessionStorage?.setItem('isAdmin', JSON.stringify(resuldado?.entidades[0].usuario?.isAdmin));
        this.router.navigate(['/app-logado']);
      }
    });
  }

}
