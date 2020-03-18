import { Component, OnInit } from '@angular/core';
import {Person} from '../../../model/domain/person.model';
import {ClienteService} from '../../../services/cliente.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Util} from "../../shared/app.util";
import {User} from "../../../model/domain/user.model";

@Component({
  selector: 'app-user-alter',
  templateUrl: './user-alter.component.html',
  styleUrls: ['./user-alter.component.css']
})
export class UserAlterComponent implements OnInit {

  client: Person = new Person();
  user: User = new User();

  constructor(private clientService: ClienteService, private router: Router, private route: ActivatedRoute, private util: Util) { }

  ngOnInit(): void {
    this.client.id = this.route.snapshot.params.id;;
    this.getCliente();
  }

  async getCliente() {
    await this.clientService.getClientes(this.client).subscribe( dado => this.client = dado.entidades[0]);
  }

  alterarCliente() {
    this.clientService.updateCliente(this.client).subscribe(
      resultado => {
        if (resultado.msg == null) {
          console.log('Produto alterado com sucesso.');
          this.client = resultado.entidades[0];
          this.router.navigate(['/app-logado', resultado.entidades[0].id]);
        }else{
          alert(this.util.getMensagensSeparadas(resultado.msg));
        }
      },
      erro => {
        switch (erro.status) {
          case 400:
            console.log(erro.error.mensagem);
            break;
          case 404:
            console.log('Produto não localizado.');
            break;
        }
      }
    );
  }
}
