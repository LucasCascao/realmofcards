import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ClienteService} from '../../../services/cliente.service';
import {Person} from '../../../model/domain/person.model';

@Component({
  selector: 'app-user-delete',
  templateUrl: './user-delete.component.html',
  styleUrls: ['./user-delete.component.css']
})
export class UserDeleteComponent implements OnInit {

  client: Person = new Person();

  constructor(private router: Router, private clienteService: ClienteService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.client = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.getCliente();
  }

  async getCliente() {
    await this.clienteService.getClientes(this.client).subscribe( dado => this.client = dado.entidades[0]);
  }

  excluirConta() {

    this.clienteService.deleteCliente(this.client).subscribe(
      resultado => {
        console.log('Produto excluído com sucesso.');
      },
      erro => {
        if ( erro.status === 404) {
          console.log('Produto não localizado.');
        }
      });

    this.router.navigate(['/']);
  }

}
