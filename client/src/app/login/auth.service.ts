/* eslint-disable no-unused-vars */
import {EventEmitter, Injectable} from '@angular/core';
import {Pessoa} from '../../model/domain/pessoa.model';
import {Router} from '@angular/router';
import {ClienteService} from '../../services/cliente.service';
import {ResultClient} from '../../model/results/result-person.model';
import {Usuario} from '../../model/domain/usuario.model';
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

  constructor(private router: Router, private service: UtilService, private util: Util) { }

  async signIn(user: Usuario) {

  }

}
