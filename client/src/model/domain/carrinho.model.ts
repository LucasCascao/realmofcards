import { Item } from './item.model';
import { Person } from './person.model';

export class Carrinho {
    id: number;
    pessoa: Person;
    itens: Array<Item>;
}