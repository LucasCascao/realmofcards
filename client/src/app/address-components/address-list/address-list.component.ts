import { Component, OnInit } from '@angular/core';
import { UtilService } from 'src/services/util.service';
import { Endereco } from 'src/model/domain/endereco.model';
import { ActivatedRoute } from '@angular/router';
import { Person } from 'src/model/domain/person.model';
import { GLOBAL } from 'src/app/shared/global.util';

@Component({
  selector: 'app-address-list',
  templateUrl: './address-list.component.html',
  styleUrls: ['./address-list.component.css']
})
export class AddressListComponent implements OnInit {

  constructor(private service: UtilService, private route: ActivatedRoute) { }

  enderecos: Endereco[];

  endereco: Endereco = new Endereco();

  ngOnInit(): void {
    this.endereco.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.getEnderecos();
  }

  async getEnderecos(){
    await this.service.get( this.endereco, 'enderecos').subscribe( resultado => {
      this.enderecos = resultado?.entidades;
    });
  }

  seleciona(id: number){
    console.log(id)
    GLOBAL.endereco = new Endereco();
    GLOBAL.endereco.id = id;
  }

}
