import { HttpClient } from '@angular/common/http';
/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import { Category } from '../../../../model/domain/category.model';
import { UtilService } from 'src/services/util.service';
import { Carta } from '../../../../model/domain/carta.model';
import {Jogo} from '../../../../model/domain/jogo.model';
import {Status} from '../../../../model/domain/status.model';
import {Util} from '../../../shared/app.util';
import {Router, ChildActivationStart} from '@angular/router';

@Component({
  selector: 'app-card-register',
  templateUrl: './card-register.component.html',
  styleUrls: ['./card-register.component.css']
})
export class CardRegisterComponent implements OnInit {

  constructor(private service: UtilService, private util: Util, private router: Router, private http: HttpClient) { }

  categorias: Category[];

  carta: Carta;

  imagemSelecionada: File;

  mensagens = [];

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
    this.mensagens = [];
    const nomeArquivo: string[] = this.carta.imagemPath.split('\\');
    this.carta.imagemPath = nomeArquivo[nomeArquivo.length - 1];
    await this.service.add(this.carta, 'cartas').subscribe(resultado => {
      if (resultado.msg != null) {
        this.mensagens = this.util.getMensagensSeparadas2(resultado?.msg);
      } else {
        this.carta = resultado?.entidades[0];
        this.router.navigate(['/app-logado/admin-page']);
      }
    });
  }

  // onChange(event) {
  //   console.log(event);
  //   if (event.target.files && event.target.files[0]) {
  //     const foto = event.srcElement.files[0] as File;
  //     document.getElementById('customFileLabel').innerHTML = foto.name;

  //     const formData = new FormData();
  //     formData.append('foto', foto);

  //     // const fotoObj = new Foto();

  //     fotoObj.foto = foto;

  //     this.http.post('http://localhost:8080/fotos', fotoObj).subscribe();
  //   }
  // }

  // inputFileChange(event) {
  //   if (event.target.files && event.target.files) {
  //     this.imagemSelecionada = event.target.files[0];
  //     const foto: File = event.target.files[0];
  //     this.carta.imagemArquivo.append('foto', foto, foto.name);
  //     console.log(this.carta.imagemArquivo);
  //   }
  // }

}
