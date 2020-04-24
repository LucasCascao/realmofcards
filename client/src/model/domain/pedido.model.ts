import { Usuario } from './usuario.model';
import { StatusPedido } from './status-pedido.model';
import { Item } from './item.model';
import {FormaPagamento} from './forma-pagamento.model';
import {Endereco} from "./endereco.model";

export class Pedido {
    id: number;
    codigoPedido: string;
    cliente: Usuario;
    valorTotal: number;
    formaPagamentoList: Array<FormaPagamento>;
    administrador: Usuario;
    statusPedido: StatusPedido;
    itemList: Array<Item>;
    endereco: Endereco;
    enderecoEscolhido: string;
    dataCompra: Date;
    dataEstimada: Date;
}
