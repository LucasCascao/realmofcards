import {Pedido} from './pedido.model';
import {ItemTroca} from './item-troca.model';

export class Troca {
  id: number;
  itemListParaTroca: Array<ItemTroca>;
  pedidoParaTroca: Pedido;
}
