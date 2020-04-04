import { Endereco } from '../domain/endereco.model';
import { CartaoCredito } from '../domain/cartao-credito.model';
import { Person } from '../domain/person.model';
import { Item } from '../domain/item.model';
import { Carrinho } from '../domain/carrinho.model';

export class PaymentPage{
    endereco: Endereco;
    cartaoCreditoList: Array<CartaoCredito>;
    carrinho: Carrinho;
    pessoa: Person;
}