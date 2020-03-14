import {EventEmitter, Injectable} from '@angular/core';
import {Person} from '../../model/person.model';
import {Router} from '@angular/router';
import {ClienteService} from '../../services/cliente.service';
import {ResultClient} from '../../model/result-person.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  clients: Person[] = [];
  resultado: ResultClient = new ResultClient();

  mostrarMenuEmitter = new EventEmitter<boolean>();

  usuarioAutenticado = false;


  constructor(private router: Router, private clienteService: ClienteService) { }

  async signIn(person: Person) {

    await this.clienteService.getClientes(person).subscribe(dado => {
      this.resultado = dado;
      this.clients = dado.entidades;
      this.clients = this.resultado.entidades;

      if (person.email === this.clients[0].email && person.password === this.clients[0].password) {
        this.usuarioAutenticado = true;
        this.mostrarMenuEmitter.emit(true);
        localStorage.setItem('userAutenticado', JSON.stringify(this.clients));
        this.router.navigate(['/product-market-page']);
        sessionStorage.setItem('clienteLogadoId', this.clients[0].id.toString());
      } else {
        alert('Login ou senha invalida');
      }
    });
  }

}
