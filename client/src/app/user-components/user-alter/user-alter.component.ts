import { Component, OnInit } from '@angular/core';
import {Pessoa} from '../../../model/domain/pessoa.model';
import {ClienteService} from '../../../services/cliente.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Util} from '../../shared/app.util';
import {Usuario} from '../../../model/domain/usuario.model';
import { UtilService } from 'src/services/util.service';

@Component({
  selector: 'app-user-alter',
  templateUrl: './user-alter.component.html',
  styleUrls: ['./user-alter.component.css']
})
export class UserAlterComponent implements OnInit {

  client: Pessoa = new Pessoa();
  user: Usuario = new Usuario();

  constructor(private service: UtilService, private router: Router, private route: ActivatedRoute, private util: Util) { }

  ngOnInit(): void {
    this.client = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.getCliente();
  }

  async getCliente() {
    await this.service.get(this.client, 'pessoas').subscribe( dado => this.client = dado.entidades[0]);
  }

  alterarCliente() {
    this.service.update(this.client, 'pessoas').subscribe(
      resultado => {
        if (resultado.msg == null) {
          this.client = resultado.entidades[0];
          this.router.navigate(['/app-logado/user-details']);
        } else {
          alert(this.util.getMensagensSeparadas(resultado.msg));
        }
      }
    );
  }
}
