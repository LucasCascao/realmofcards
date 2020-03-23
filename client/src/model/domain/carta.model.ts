import {Category} from './category.model';
import { Jogo } from './jogo.model';
import { Status } from './status.model';

export class Card {
  id: number;
  nome: string;
  descricao: string;
  valorCompra: number;
  precificacao: number;
  valorVenda: number;
  imagemPath: string;
  jogo: Jogo;
  categoriaCarta: Category;
  status: Status;
}
