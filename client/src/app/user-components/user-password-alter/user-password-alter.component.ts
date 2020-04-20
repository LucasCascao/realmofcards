import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/model/domain/usuario.model';
import {UtilService} from '../../../services/util.service';

@Component({
  selector: 'app-user-password-alter',
  templateUrl: './user-password-alter.component.html',
  styleUrls: ['./user-password-alter.component.css']
})
export class UserPasswordAlterComponent implements OnInit {

  usuario: Usuario;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.usuario = JSON.parse(sessionStorage.getItem('pessoaLogada'))?.usuario;
  }

  alterarSenha() {
    this.service.update(this.usuario, 'usuarios').subscribe();
  }

}
