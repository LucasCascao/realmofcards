import { User } from './user.model';
import { StatusPedido } from './status-pedido.model';
import { Item } from './item.model';

export class Pedido {
    id: number;
    dataCompra: Date;
    cliente: User;
    administrador: User;
    statusPedido: StatusPedido;
    items: Array<Item>;
}