import {Item} from './item.model';

export class FormaPagamento {
  id: number;
  valorTotal: number;
  quantidadeParcelas: number;
  valorParcelado: number;
  cartaoCreditoList: Array<Item>;
}
