import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../services/util.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Endereco} from '../../../model/domain/endereco.model';
import {Pessoa} from '../../../model/domain/pessoa.model';
import {Util} from '../../shared/app.util';
import {Estado} from '../../../model/domain/estado.model';

@Component({
  selector: 'app-address-alter',
  templateUrl: './address-alter.component.html',
  styleUrls: ['./address-alter.component.css']
})
export class AddressAlterComponent implements OnInit {

  constructor(private service: UtilService, private route: ActivatedRoute, private router: Router, private util: Util) { }

  endereco: Endereco = new Endereco();

  estados: Estado[];

  ngOnInit(): void {
    this.endereco = JSON.parse(sessionStorage.getItem('enderecoSelecionado'));
    this.endereco.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.estados = [];
    this.getEstados();
  }

  getEstados() {
    this.service.get(new Estado(), 'estados').subscribe( resultado => {
      this.estados = resultado?.entidades;
      this.getEnderecos();
    });
  }

  async getEnderecos() {
    await this.service.get( this.endereco, 'enderecos').subscribe( resultado => {
      this.endereco = resultado?.entidades[0];
    });
  }

  async alterarEndereco() {
    await this.service.update(this.endereco, 'enderecos').subscribe(resultado => {
      if (resultado.msg == null) {
        this.router.navigate(['/app-logado/address-list']);
      } else {
        alert(this.util.getMensagensSeparadas(resultado?.msg));
      }
    });
  }
}
