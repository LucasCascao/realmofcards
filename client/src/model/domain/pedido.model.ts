/* eslint-disable no-unused-vars */
import { StatusPedido } from './status-pedido.model';
import { Item } from './item.model';
import {FormaPagamento} from './forma-pagamento.model';
import {Endereco} from "./endereco.model";
import {Pessoa} from "./pessoa.model";

export class Pedido {
  id: number;
  codigoPedido: string;
  cliente: Pessoa;
  valorTotal: number;
  formaPagamentoList: Array<FormaPagamento>;
  administrador: Pessoa;
  statusPedido: StatusPedido;
  itemList: Array<Item>;
  endereco: Endereco;
  enderecoEscolhido: string;
  dataCompra: Date;
  dataEstimada: Date;
}
