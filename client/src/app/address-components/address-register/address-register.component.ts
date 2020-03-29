import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../services/util.service';
import {Router} from '@angular/router';
import {Endereco} from "../../../model/domain/endereco.model";
import {GLOBAL} from "../../shared/global.util";
import {Util} from "../../shared/app.util";
import { Estado } from 'src/model/domain/estado.model';
import { Cidade } from 'src/model/domain/cidade.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-address-register',
  templateUrl: './address-register.component.html',
  styleUrls: ['./address-register.component.css']
})
export class AddressRegisterComponent implements OnInit {

  endereco: Endereco;

  estados: Estado[];

  constructor(private service: UtilService, private router: Router, private util: Util) { }

  ngOnInit(): void {
    this.endereco = new Endereco();
    this.endereco.cidade = new Cidade();
    this.endereco.cidade.estado = new Estado();
    this.endereco.pessoa = GLOBAL.pessoa;
    this.estados = [];
    console.log(GLOBAL.pessoa);
    this.getEstados();
  }

  async getEstados() {
    await this.service.get(new Estado(), 'estados').subscribe( resultado => {
      this.estados = resultado?.entidades;
    });
  }

  async cadastra() {
    await this.service.add(this.endereco, 'enderecos').subscribe(resultado => {
      if (resultado.msg == null) {
        this.router.navigate(['/app-logado/address-list']);
      } else {
        alert(this.util.getMensagensSeparadas(resultado.msg));
      }
    });
  }
}
