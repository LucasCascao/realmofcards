import {EventEmitter, Injectable} from '@angular/core';
import {Client} from '../../model/client.model';
import {Router} from '@angular/router';
import {ClienteService} from '../../services/cliente.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private usuarioAutenticado = false;

  clients: Client[];

  mostrarMenuEmitter = new EventEmitter<boolean>();

  constructor(private router: Router, private clienteService: ClienteService) { }

  signIn(client: Client) {

    this.clienteService.getClientes(client).subscribe(dado => {
      this.clients = dado.entidades;
    });

    if (client.email === this.clients[0].email && client.password === this.clients[0].password) {
      this.usuarioAutenticado = true;
      this.mostrarMenuEmitter.emit(true);
      this.router.navigate(['/product-market-page']);
      sessionStorage.setItem('clienteLogadoId', this.clients[0].id.toString());
      sessionStorage.setItem('clienteLogadoNome', this.clients[0].nome);
      sessionStorage.setItem('clienteLogadoEmail', this.clients[0].email);
      sessionStorage.setItem('clienteLogadoSobrenome', this.clients[0].sobrenome);
      sessionStorage.setItem('clienteLogadoCpf', this.clients[0].cpf);
      sessionStorage.setItem('clienteLogadoUsername', this.clients[0].username);
      sessionStorage.setItem('clienteLogadoDataNascimento', this.clients[0].dataNascimento.toString());
    }
  }

}
