import { Component, OnInit } from '@angular/core';
import {MockCards} from '../../../../mock/mock-card.model';
import {Carta} from '../../../../model/domain/carta.model';
import {UtilService} from '../../../../services/util.service';
import {Router} from '@angular/router';
import {Status} from '../../../../model/domain/status.model';

@Component({
  selector: 'app-card-active',
  templateUrl: './card-active.component.html',
  styleUrls: ['./card-active.component.css']
})
export class CardActiveComponent implements OnInit {

  carta: Carta;

  constructor(private service: UtilService,
              private router: Router) { }

  ngOnInit(): void {
    this.carta = JSON.parse(sessionStorage.getItem('cartaInativaSelecionada'));
  }

  ativaCarta() {
    if (this.carta.quantidadeDisponivel > 0) {
      const statusAtivo = new Status();
      statusAtivo.id = 1;
      this.carta.status = statusAtivo;
      this.carta.quantidadeEstoque = this.carta.quantidadeDisponivel;
      this.service.update(this.carta, 'cartas').subscribe(resultado => {
        if (resultado.msg == null) {
          this.router.navigate(['/app-logado/admin-product-inactive-list']);
        }
      });
    } else {
      alert('Valor invalido para inserir no estoque');
    }
  }

}
