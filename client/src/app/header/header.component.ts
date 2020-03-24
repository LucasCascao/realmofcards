import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Person} from '../../model/domain/person.model';
import {ClienteService} from '../../services/cliente.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  client: Person = new Person();

  @Input()
  id?;

  constructor(private route: ActivatedRoute, private clienteService: ClienteService) { }

  ngOnInit() {
    this.client.id = this.id;
    this.getUser();
  }

  async getUser() {
    await this.clienteService.getClientes(this.client).subscribe(resultado => {
      this.client = resultado?.entidades[0];
    });
  }

}
