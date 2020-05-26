/* eslint-disable no-unused-vars */
import { CartaoCredito } from './cartao-credito.model';
import { Cupom } from './cupom.model';

export class FormaPagamento {
  id: number;
  valorPagamento: number;
  cartaoCredito: CartaoCredito;
  cupom: Cupom;
  isSelecionado: boolean;
}
