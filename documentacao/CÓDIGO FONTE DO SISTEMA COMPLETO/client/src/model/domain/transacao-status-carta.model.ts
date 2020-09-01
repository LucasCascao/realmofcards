/* eslint-disable no-unused-vars */
import { Carta } from "./carta.model";
import { Status } from './status.model';

export class TransacaoStatusCarta {
  id: number;
  motivo: string;
  carta: Carta
  status: Status;
}
