/* eslint-disable no-unused-vars */
import { Component, OnInit } from '@angular/core';
import { Carta } from 'src/model/domain/carta.model';
import { UtilService } from 'src/services/util.service';
import { Status } from 'src/model/domain/status.model';
import { Util } from 'src/app/shared/app.util';
import { Category } from 'src/model/domain/category.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-stock-insert',
  templateUrl: './stock-insert.component.html',
  styleUrls: ['./stock-insert.component.css']
})
export class StockInsertComponent implements OnInit {

  cartas: Carta[];

  cartasFiltradas: Carta[];

  valorBuscado: string;

  mensagens: string[];

  constructor(private service: UtilService,
              private util: Util,
              private router: Router) { }

  ngOnInit(): void {
    this.mensagens = [];
    this.getCartasAtivas();
  }

  async getCartasAtivas() {
    const carta: Carta = new Carta();
    carta.status = new Status();
    carta.status.id = 1;

    this.service.get(carta, 'cartas').subscribe(resultado => {
      this.cartas = resultado?.entidades;
      this.cartasFiltradas = resultado?.entidades;
    });
  }

  filtrar() {
    this.cartasFiltradas = this.cartas.filter((carta) =>
      carta?.nome?.toUpperCase().startsWith(this.valorBuscado.toUpperCase())
      || carta?.codigo?.startsWith(this.valorBuscado)
    );
  }

  alterar(cartaAlterada: Carta){
    this.service.update(cartaAlterada, 'cartas').subscribe(resultado => {
      if(resultado?.msg == null) {
        this.getCartasAtivas();
      } else {
        this.mensagens = this.util.getMensagensSeparadas2(resultado?.msg);
      }
    });
  }

  adcionar(cartaSemAlteracao: Carta) {
    const cartaAlterada = cartaSemAlteracao;
    const qntdParaAdcionar = cartaSemAlteracao.estoqueAlterar;
    if(qntdParaAdcionar > 0){
      cartaAlterada.quantidadeEstoque += qntdParaAdcionar;
      cartaAlterada.quantidadeDisponivel += qntdParaAdcionar;
      this.alterar(cartaAlterada);
    } else {
      this.insereMensagemValorZerado();
    }
  }

  retirar(cartaSemAlteracao: Carta) {
    const cartaAlterada = cartaSemAlteracao;
    const qntdParaRetirar = cartaSemAlteracao.estoqueAlterar;
    const qntdEstoqueAtual = cartaSemAlteracao.quantidadeEstoque;
    if(qntdParaRetirar > 0){
      if(qntdParaRetirar <= qntdEstoqueAtual){
        if(qntdEstoqueAtual - qntdParaRetirar == 0){
          this.seleciona(cartaSemAlteracao);
          this.inativa(cartaSemAlteracao);
        } else {
          cartaAlterada.quantidadeEstoque -= qntdParaRetirar;
          cartaAlterada.quantidadeDisponivel -= qntdParaRetirar;
          this.alterar(cartaAlterada);
        }
      }
    } else {
      this.insereMensagemValorZerado();
    }
  }

  inativa(carta: Carta){
    this.seleciona(carta);
    this.router.navigate(['/app-logado/admin-page/admin-product-delete']);
  }

  seleciona(carta: Carta) {
    sessionStorage.setItem('cartaSelecionada', JSON.stringify(carta));
  }

  insereMensagemValorZerado(){
    this.mensagens.push("Valor para altarar não pode ser 0");
  }
}
