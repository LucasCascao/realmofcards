import { User } from './user.model';
import { StatusPedido } from './status-pedido.model';
import { Item } from './item.model';
import {FormaPagamento} from './forma-pagamento.model';
import {Endereco} from "./endereco.model";

export class Pedido {
    id: number;
    codigoPedido: string;
    cliente: User;
    valorTotal: number;
    formaPagamento: FormaPagamento;
    administrador: User;
    statusPedido: StatusPedido;
    itemList: Array<Item>;
    endereco: Endereco;
}
