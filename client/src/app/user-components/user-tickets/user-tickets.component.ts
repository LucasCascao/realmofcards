import { Component, OnInit } from '@angular/core';
import { UtilService } from 'src/services/util.service';
import { Cupom } from 'src/model/domain/cupom.model';

@Component({
  selector: 'app-user-tickets',
  templateUrl: './user-tickets.component.html',
  styleUrls: ['./user-tickets.component.css']
})
export class UserTicketsComponent implements OnInit {

  cupons: Cupom[];

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.cupons = [];
    this.getCupons();
  }

  getCupons(){
    let cupom: Cupom = new Cupom();
    cupom.pessoa = JSON.parse(sessionStorage.getItem('pessoaLogada'));
    this.service.get(cupom, 'cupons').subscribe(resultado => {
      this.cupons = resultado?.entidades;
    })
  }

}
