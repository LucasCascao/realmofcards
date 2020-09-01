import {Pedido} from './pedido.model';
import {Item} from './item.model';
import {Transicao} from './transicao.model';

export class ItemTransicao {
  id: number;
  quantidade: number;
  item: Item;
  troca: Transicao;
}
