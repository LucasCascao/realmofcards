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

  // enderecos = [
  //   {
  //     logradouro : 'Rua Passos',
  //     numero : '876',
  //     bairro : 'Cidade Edson',
  //     cidade : 'Suzano',
  //     estado : 'SP',
  //     cep : '08665-410',
  //     complemento : 'Casa do muro laranja com o portão branco'
  //   },
  //   {
  //     logradouro : 'Rua João Pereira dos Santos',
  //     numero : '70',
  //     bairro : 'Jd. Nova Poá',
  //     cidade : 'Poá',
  //     estado : 'SP',
  //     cep : '08568-020',
  //     complemento : 'Condominio Village 1'
  //   }
  // ];

  enderecos: Endereco[];

  endereco: Endereco;

  ngOnInit(): void {
    this.endereco = new Endereco();
    this.endereco.pessoa = new Person();
    this.endereco.pessoa.id = GLOBAL.pessoa.id;
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
