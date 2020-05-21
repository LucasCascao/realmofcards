/* eslint-disable no-unused-vars */
import { Status } from './status.model';
import { Troca } from './troca.model';

export class Cupom {
    id: number;
    codigo: string;
    valor: number;
    status: Status;
    troca: Troca;
}
