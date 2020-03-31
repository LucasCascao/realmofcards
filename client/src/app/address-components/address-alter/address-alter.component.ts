import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../services/util.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Endereco} from '../../../model/domain/endereco.model';
import {Person} from '../../../model/domain/person.model';
import {GLOBAL} from '../../shared/global.util';
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
    this.endereco.pessoa = new Person();
    this.endereco.pessoa.id = GLOBAL.pessoa.id;
    console.log(GLOBAL.pessoa.id);
    this.getEnderecos();
  }

  async getEnderecos() {
    await this.service.get( this.endereco, 'enderecos').subscribe( resultado => {
      this.endereco = resultado?.entidades[0];
      console.log()
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
