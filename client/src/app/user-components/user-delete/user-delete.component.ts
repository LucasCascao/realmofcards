/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ClienteService} from '../../../services/cliente.service';
import {Pessoa} from '../../../model/domain/pessoa.model';
import {UtilService} from "../../../services/util.service";

@Component({
  selector: 'app-user-delete',
  templateUrl: './user-delete.component.html',
  styleUrls: ['./user-delete.component.css']
})
export class UserDeleteComponent implements OnInit {

  client: Pessoa = new Pessoa();

  constructor(private router: Router, private service: UtilService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.client = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.getCliente();
  }

  async getCliente() {
    await this.service.get(this.client, 'pessoas').subscribe( dado => this.client = dado.entidades[0]);
  }

  excluirConta() {

    this.service.delete(this.client.id, 'pessoas').subscribe(
      resultado => {
        sessionStorage.removeItem('pessoaLogada');
        this.router.navigate(['/']);
      },
      erro => {
        if ( erro.status === 404) {
          console.log('Produto não localizado.');
        }
      });
  }

}
