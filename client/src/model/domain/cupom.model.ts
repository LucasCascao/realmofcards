/* eslint-disable no-unused-vars */
import { Status } from './status.model';
import { Pessoa } from './pessoa.model';

export class Cupom {
  id: number;
  codigo: string;
  valor: number;
  status: Status;
  pessoa: Pessoa;
  dataCriacao: Date;
}
