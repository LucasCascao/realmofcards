import {EventEmitter, Injectable} from '@angular/core';
import {Usuario} from '../../model/usuario.model';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private usuarioAutenticado = false;

  mostrarMenuEmitter = new EventEmitter<boolean>();

  constructor(private router: Router) { }

  signIn(usuario: Usuario) {
    if (usuario.username === 'lucas.cascao@gmail.com' && usuario.password === '123456') {
      this.usuarioAutenticado = true;
      this.mostrarMenuEmitter.emit(true);
      this.router.navigate(['/product-market-page']);
    } else {
      this.usuarioAutenticado = false;
      this.mostrarMenuEmitter.emit(false);
    }
  }

}
