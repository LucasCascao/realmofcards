import { Component, OnInit } from '@angular/core';
import {Pessoa} from '../../../model/domain/person.model';
import {ClienteService} from '../../../services/cliente.service';
import {ActivatedRoute} from '@angular/router';
import {UtilService} from '../../../services/util.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  client: Pessoa = new Pessoa();

  constructor(private clientService: ClienteService, private route: ActivatedRoute, private service: UtilService) { }

  ngOnInit(): void {
    this.client = JSON.parse(sessionStorage.getItem('pessoaLogada'));
  }

}
