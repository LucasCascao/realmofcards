import { Component, OnInit } from '@angular/core';
import { UtilService } from 'src/services/util.service';
import { Endereco } from 'src/model/domain/endereco.model';
import { ActivatedRoute, Router } from '@angular/router';
import { Pessoa } from 'src/model/domain/pessoa.model';
import { Status } from 'src/model/domain/status.model';

@Component({
  selector: 'app-address-list',
  templateUrl: './address-list.component.html',
  styleUrls: ['./address-list.component.css']
})
export class AddressListComponent implements OnInit {

  constructor(private service: UtilService, private router: Router) { }

  enderecos: Endereco[];

  endereco: Endereco = new Endereco();

  ngOnInit(): void {
    this.endereco.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.endereco.status = new Status();
    this.endereco.status.id = 1;
    this.getEnderecos();
  }

  async getEnderecos(){
    await this.service.get( this.endereco, 'enderecos').subscribe( resultado => {
      this.enderecos = resultado?.entidades;
    });
  }

  seleciona(enderecoSelecionado: Endereco) {
    sessionStorage.setItem('enderecoSelecionado', JSON.stringify(enderecoSelecionado));
  }

  inserePaginaParaRetornoNaSessao(enderecoPagina: String) {
    sessionStorage.setItem('paginaParaRetorno', JSON.stringify('/app-logado/address-list'));
  }

  enviarPara(endereco: Endereco, enderecoPagina: String) {
    this.seleciona(endereco);
    this.inserePaginaParaRetornoNaSessao(enderecoPagina);
    this.router.navigate([enderecoPagina]);
  }

}
