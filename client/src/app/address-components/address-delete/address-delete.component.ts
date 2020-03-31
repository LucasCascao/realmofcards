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
    this.endereco = new Endereco();
    this.endereco.id = this.route.snapshot.params.id;
    this.getEndereco();
  }

  async getEndereco(){
    await this.service.get(this.endereco, 'enderecos').subscribe( resultado => {
      this.endereco = resultado?.entidades[0];
    });
  }

  async deleteEndereco() {
    await this.service.delete(this.endereco.id, 'enderecos').subscribe(() =>{
      this.router.navigate(['/app-logado/address-list']);
    });
  }

}
