import { Component, OnInit } from '@angular/core';
import {MockCards} from "../../../mock/mock-card.model";
import { ActivatedRoute } from '@angular/router';
import { Carta } from 'src/model/domain/carta.model';
import { UtilService } from 'src/services/util.service';
import { async } from '@angular/core/testing';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  constructor(private route: ActivatedRoute, private service: UtilService) { }

  carta: Carta = new Carta;

  ngOnInit(): void {
    this.carta.id = this.route.snapshot.params['id'];
    this.getCarta();
  }

  async getCarta(){
    await this.service.get(this.carta, 'cartas').subscribe(resultado => {
      this.carta = resultado?.entidades[0];
      console.log(resultado);
    });
  }
}
