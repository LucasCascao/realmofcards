import { Endereco } from '../domain/endereco.model';
import { CartaoCredito } from '../domain/cartao-credito.model';

export class PaymentPageDTO{
    endereco: Endereco;
    cartaoCredito1: CartaoCredito;
    cartaoCredito2: CartaoCredito;
}