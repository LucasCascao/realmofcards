import { User } from './user.model';
import { StatusPedido } from './status-pedido.model';

export class Pedido {
    id: number;
    dataCompra: Date;
    cliente: User;
    administrador: User;
    statusPedido: StatusPedido;
}