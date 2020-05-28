import { Item } from './item.model';
import { Pessoa } from './pessoa.model';

export class Carrinho {
    id: number;
    pessoa: Pessoa;
    itemList: Array<Item>;
}
