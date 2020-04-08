import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../services/util.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Endereco} from '../../../model/domain/endereco.model';
import {Person} from '../../../model/domain/person.model';
import {Util} from '../../shared/app.util';

@Component({
  selector: 'app-address-alter',
  templateUrl: './address-alter.component.html',
  styleUrls: ['./address-alter.component.css']
})
export class AddressAlterComponent implements OnInit {

  constructor(private service: UtilService, private route: ActivatedRoute, private router: Router, private util: Util) { }

  endereco: Endereco = new Endereco();

  ngOnInit(): void {
    this.endereco = JSON.parse(sessionStorage.getItem('enderecoSelecionado'));
    this.endereco.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.getEnderecos();
  }

  async getEnderecos() {
    await this.service.get( this.endereco, 'enderecos').subscribe( resultado => {
      this.endereco = resultado?.entidades[0];
    });
  }

  async alterarEndereco() {
    await this.service.update(this.endereco, 'endereco').subscribe(resultado => {
      if(resultado.msg == null) {
        this.router.navigate(['/app-logado/address-list']);
      } else {
        alert(this.util.getMensagensSeparadas(resultado?.msg));
      }
    });
  }
}
