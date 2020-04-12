import { Item } from './item.model';
import { Pessoa } from './person.model';

export class Carrinho {
    id: number;
    pessoa: Pessoa;
    itens: Array<Item>;
}
