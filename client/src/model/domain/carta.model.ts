/* eslint-disable no-unused-vars */
import {Category} from './category.model';
import { Jogo } from './jogo.model';
import { Status } from './status.model';
import { GrupoPrecificacao } from './grupo-precificacao.model';

export class Carta {
  id: number;
  codigo: string;
  nome: string;
  descricao: string;
  valorCompra: number;
  grupoPrecificacao: GrupoPrecificacao;
  quantidadeDisponivel: number;
  quantidadeEstoque: number;
  imagemPath: string;
  imagemArquivo: FormData;
  jogo: Jogo;
  categoriaCarta: Category;
  status: Status;
  estoqueAlterar: number;
}
