import {EventEmitter, Injectable} from '@angular/core';
import {Client} from '../../model/client.model';
import {Router} from '@angular/router';
import {ClienteService} from '../../services/cliente.service';
import {ResultClient} from '../../model/result-client.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  clients: Client[] = [];
  resultado: ResultClient = new ResultClient();

  mostrarMenuEmitter = new EventEmitter<boolean>();

  usuarioAutenticado = false;


  constructor(private router: Router, private clienteService: ClienteService) { }

  async signIn(client: Client) {

    this.clienteService.getClientes(client).subscribe(dado => {
      this.resultado = dado;
      this.clients = dado.entidades;
    });

    this.clients = this.resultado.entidades;

    if (client.email === this.clients[0].email && client.password === this.clients[0].password) {
      this.usuarioAutenticado = true;
      this.mostrarMenuEmitter.emit(true);
      localStorage.setItem('userAutenticado', JSON.stringify(this.clients));
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
