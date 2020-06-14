import {Category} from './category.model';
import { Jogo } from './jogo.model';
import { Status } from './status.model';

export class Carta {
  id: number;
  codigo: string;
  nome: string;
  descricao: string;
  valorCompra: number;
  precificacao: number;
  valorVenda: number;
  quantidadeDisponivel: number;
  quantidadeEstoque: number;
  imagemPath: string;
  imagemArquivo: FormData;
  jogo: Jogo;
  categoriaCarta: Category;
  status: Status;
  selecionadoAlterar: boolean;
}
