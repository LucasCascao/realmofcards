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

  id;

  constructor(private router: Router, private clienteService: ClienteService, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  excluirConta() {

    const client = new Person();

    // tslint:disable-next-line:radix
    client.id = this.route.snapshot.params.id;

    this.clienteService.deleteCliente(client).subscribe(
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
