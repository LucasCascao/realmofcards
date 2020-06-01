import {Pedido} from './pedido.model';
import {ItemTransicao} from './item-transicao.model';
import { Cupom } from './cupom.model';
import { StatusTransacao } from './status-transicao';
import { TipoTransicao } from './tipo-transicao';

export class Transicao {
  id: number;
  codigo: string;
  itemTransicaoList: Array<ItemTransicao>;
  pedido: Pedido;
  subTotal: number;
  cupom: Cupom;
  statusTransacao: StatusTransacao;
  tipoTransicao: TipoTransicao;
}
