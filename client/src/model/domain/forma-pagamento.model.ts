/* eslint-disable no-unused-vars */
import { CartaoCredito } from './cartao-credito.model';

export class FormaPagamento {
  id: number;
  registroCartao: string;
  valorPagamento: number;
  cartao: CartaoCredito;
  isSelecionado: boolean;
}
