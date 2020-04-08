import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Person} from '../../model/domain/person.model';
import {ClienteService} from '../../services/cliente.service';
import { UtilService } from 'src/services/util.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private route: ActivatedRoute, private clienteService: ClienteService, private service: UtilService) { }

  ngOnInit() {
  }

  logout() {
    sessionStorage.removeItem('usuarioLogado');
    sessionStorage.removeItem('pessoaLogada');
  }

}
