import { Component, OnInit } from '@angular/core';
import {UtilService} from '../../../../services/util.service';
import {Carta} from '../../../../model/domain/carta.model';
import {ActivatedRoute, Route, Router} from '@angular/router';

@Component({
  selector: 'app-card-delete',
  templateUrl: './card-delete.component.html',
  styleUrls: ['./card-delete.component.css']
})
export class CardDeleteComponent implements OnInit {

  constructor(private service: UtilService, private route: ActivatedRoute, private router: Router) { }

  carta: Carta;

  ngOnInit(): void {
    this.carta = JSON.parse(sessionStorage.getItem('cartaSelecionada'));
    this.getCarta();
  }

  async getCarta() {
    await this.service.get(this.carta, 'cartas').subscribe( resultado => {
      this.carta = resultado?.entidades[0];
    });
  }

  async deletaCarta() {
    this.carta.quantidadeDisponivel = 0;
    this.carta.quantidadeEstoque = 0;
    await this.service.delete(this.carta?.id, 'cartas').subscribe(resultado => this.naveguePara());
  }

  naveguePara() {
    this.router.navigate(['/app-logado/admin-page']);
  }

}
