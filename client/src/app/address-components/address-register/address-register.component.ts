import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../services/util.service';
import {Router} from '@angular/router';
import {Endereco} from "../../../model/domain/endereco.model";
import {GLOBAL} from "../../shared/global.util";

@Component({
  selector: 'app-address-register',
  templateUrl: './address-register.component.html',
  styleUrls: ['./address-register.component.css']
})
export class AddressRegisterComponent implements OnInit {

  endereco: Endereco;

  constructor(private service: UtilService, private router: Router) { }

  ngOnInit(): void {
    this.endereco = new Endereco();
    this.endereco.pessoa = GLOBAL.pessoa;
  }

  async cadastra() {

    await this.service.add(this.endereco, 'enderecos').subscribe(() => {
      this.router.navigate(['/app-logado/address-list']);
    });
  }

}
