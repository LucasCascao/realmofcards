import {Pedido} from './pedido.model';
import {Item} from './item.model';
import {Troca} from './troca.model';

export class ItemTroca {
  id: number;
  quantidade: number;
  itemParaTroca: Item;
  troca: Troca;
}
