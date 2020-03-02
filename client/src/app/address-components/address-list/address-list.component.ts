import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-address-list',
  templateUrl: './address-list.component.html',
  styleUrls: ['./address-list.component.css']
})
export class AddressListComponent implements OnInit {

  constructor() { }

  enderecos = [
    {
      logradouro : 'Rua Passos',
      numero : '876',
      bairro : 'Cidade Edson',
      cidade : 'Suzano',
      estado : 'SP',
      cep : '08665-410',
      complemento : 'Casa do muro laranja com o portão branco'
    },
    {
      logradouro : 'Rua João Pereira dos Santos',
      numero : '70',
      bairro : 'Jd. Nova Poá',
      cidade : 'Poá',
      estado : 'SP',
      cep : '08568-020',
      complemento : 'Condominio Village 1'
    }
  ];

  ngOnInit(): void {
  }

}
