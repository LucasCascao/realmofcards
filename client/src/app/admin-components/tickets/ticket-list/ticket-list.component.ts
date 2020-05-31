import { Component, OnInit } from '@angular/core';
import { Cupom } from 'src/model/domain/cupom.model';
import { UtilService } from 'src/services/util.service';

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent implements OnInit {

  cupons: Cupom[];

  cuponsFiltrado: Cupom[];

  valorBuscado: string;

  constructor(private service: UtilService) { }

  ngOnInit(): void {
    this.cupons = [];
    this.getCupons();
  }

  getCupons(){
    let cupom: Cupom = new Cupom();
    this.service.get(cupom, 'cupons').subscribe(resultado => {
      this.cupons = resultado?.entidades;
      this.cuponsFiltrado = this.cupons;
    });
  }

  filtrar() {
    this.cuponsFiltrado = this.cupons.filter((cupom) =>
      cupom?.codigo.startsWith(this.valorBuscado)
      || cupom?.pessoa?.usuario?.email.startsWith(this.valorBuscado)
    );
  }

}
