import {CartaoCredito} from './cartao-credito.model';

export class FormaPagamento {
  id: number;
  valorTotal: number;
  quantidadeParcelas: number;
  valorParcelado: number;
  cartaoCreditoList: Array<CartaoCredito>;
}
