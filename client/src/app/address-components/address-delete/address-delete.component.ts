/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import { UtilService } from 'src/services/util.service';
import { Endereco } from 'src/model/domain/endereco.model';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-address-delete',
  templateUrl: './address-delete.component.html',
  styleUrls: ['./address-delete.component.css']
})
export class AddressDeleteComponent implements OnInit {

  endereco: Endereco;

  constructor(private service: UtilService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.endereco = JSON.parse(sessionStorage.getItem('enderecoSelecionado'));
    this.getEndereco();
  }

  async getEndereco(){
    await this.service.get(this.endereco, 'enderecos').subscribe( resultado => {
      this.endereco = resultado?.entidades[0];
    });
  }

  async deleteEndereco() {
    await this.service.delete(this.endereco.id, 'enderecos').subscribe(() =>{
      let paginaRetorno = JSON.parse(sessionStorage.getItem('paginaParaRetorno'));
      this.router.navigate([paginaRetorno]);
    });
  }

}
