import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Pessoa} from '../../model/domain/pessoa.model';
import {ClienteService} from '../../services/cliente.service';
import { UtilService } from 'src/services/util.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isAdmin

  constructor() { }

  ngOnInit() {
    this.isAdmin = JSON.parse(sessionStorage.getItem('isAdmin'));
  }

  logout() {
    sessionStorage.removeItem('usuarioLogado');
    sessionStorage.removeItem('pessoaLogada');
  }

}
