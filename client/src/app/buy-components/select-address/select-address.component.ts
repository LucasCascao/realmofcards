import { Component, OnInit } from '@angular/core';
import {UtilService} from "../../../services/util.service";
import {ActivatedRoute} from "@angular/router";
import {Endereco} from "../../../model/domain/endereco.model";

@Component({
  selector: 'app-select-address',
  templateUrl: './select-address.component.html',
  styleUrls: ['./select-address.component.css']
})
export class SelectAddressComponent implements OnInit {

  constructor(private service: UtilService, private route: ActivatedRoute) { }

  enderecos: Endereco[];

  endereco: Endereco = new Endereco();

  ngOnInit(): void {
    this.endereco.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.getEnderecos();
  }

  async getEnderecos() {
    await this.service.get( this.endereco, 'enderecos').subscribe( resultado => {
      this.enderecos = resultado?.entidades;
    });
  }

  seleciona(id: number){
    const endereco = new Endereco();
    endereco.id = id;
    sessionStorage.setItem('enderecoSelecionado', JSON.stringify(endereco));
  }
}
