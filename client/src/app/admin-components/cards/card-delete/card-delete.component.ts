import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../../services/util.service';
import {Carta} from '../../../../model/domain/carta.model';
import {ActivatedRoute, Route, Router} from '@angular/router';
import {GLOBAL} from "../../../shared/global.util";

@Component({
  selector: 'app-card-delete',
  templateUrl: './card-delete.component.html',
  styleUrls: ['./card-delete.component.css']
})
export class CardDeleteComponent implements OnInit {

  constructor(private service: UtilService, private route: ActivatedRoute, private router: Router) { }

  carta: Carta;

  ngOnInit(): void {
    this.carta = new Carta();
    this.carta.id = GLOBAL.carta.id;
    this.getCarta();
  }

  async getCarta(){
    await this.service.get(this.carta, 'cartas').subscribe( resultado => {
      this.carta = resultado?.entidades[0];
    });
  }

  async deletaCarta() {
    await this.service.delete(this.carta?.id, 'cartas').subscribe(resultado => this.naveguePara());
  }

  naveguePara() {
    this.router.navigate(['/app-logado/admin-page']);
  }

}
