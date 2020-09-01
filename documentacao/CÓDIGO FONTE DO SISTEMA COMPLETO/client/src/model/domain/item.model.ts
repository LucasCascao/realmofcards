/* eslint-disable no-unused-vars */
import { Carta } from './carta.model';
import { Status } from './status.model';

export class Item {
  id: number;
  carta: Carta;
  quantidade: number;
  quantidadeTroca: number;
  quantidadeDevolucao: number;
  statusItem: Status;
}
