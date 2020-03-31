import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Person} from '../../model/domain/person.model';
import {ClienteService} from '../../services/cliente.service';
import { GLOBAL } from '../shared/global.util';
import { UtilService } from 'src/services/util.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  client: Person = new Person();

  constructor(private route: ActivatedRoute, private clienteService: ClienteService, private service: UtilService) { }

  ngOnInit() {
    // this.client.id = GLOBAL.pessoa.usuario.id;
    // this.getUser();
    console.log(GLOBAL);
  }

  // async getUser() {
  //   await this.service.get(this.client, 'pessoas').subscribe(resultado => {
  //     this.client =resultado?.entidades[0];
  //   });
  // }

}
