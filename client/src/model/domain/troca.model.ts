import {Pedido} from './pedido.model';
import {ItemTroca} from './item-troca.model';
import { Cupom } from './cupom.model';

export class Troca {
  id: number;
  itemListParaTroca: Array<ItemTroca>;
  pedidoParaTroca: Pedido;
  subTotal: number;
  cupom: Cupom;
}
