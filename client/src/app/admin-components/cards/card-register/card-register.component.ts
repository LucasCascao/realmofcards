import { Component, OnInit } from '@angular/core';
import { Category } from '../../../../model/domain/category.model';
import { UtilService } from 'src/services/util.service';
import { Carta } from '../../../../model/domain/carta.model';
import {Jogo} from '../../../../model/domain/jogo.model';
import {Status} from '../../../../model/domain/status.model';
import {Util} from '../../../shared/app.util';
import {Router, ChildActivationStart} from "@angular/router";

@Component({
  selector: 'app-card-register',
  templateUrl: './card-register.component.html',
  styleUrls: ['./card-register.component.css']
})
export class CardRegisterComponent implements OnInit {

  constructor(private service: UtilService, private util: Util, private router: Router) { }

  categorias: Category[];

  carta: Carta;

  imagemSelecionada: File;

  ngOnInit(): void {
    this.imagemSelecionada = null;
    this.carta = new Carta();
    this.carta.categoriaCarta = new Category();
    this.carta.status = new Status();
    this.carta.status.id = 1;
    this.carta.jogo = new Jogo();
    this.carta.jogo.id = 2;
    this.carta.imagemArquivo = new FormData();
    this.getCategorias();
  }

  async getCategorias() {
    await this.service.get(new Category(), 'categorias').subscribe(resultado => {
      this.categorias = resultado?.entidades;
    });
  }

  async cadastraCarta() {
    await this.service.add(this.carta, 'cartas').subscribe(resultado =>{
      if (resultado.msg != null) {
        alert(this.util.getMensagensSeparadas(resultado?.msg));
      } else{
        this.carta = resultado?.entidades[0];
        this.router.navigate(['/app-logado/admin-page']);
      }
    });
  }

  inputFileChange(event) {
    if (event.target.files && event.target.files) {
      this.imagemSelecionada = event.target.files[0];
      const foto: File = event.target.files[0];
      this.carta.imagemArquivo.append('foto', foto, foto.name);
      console.log(this.carta.imagemArquivo);
    }
  }

}
