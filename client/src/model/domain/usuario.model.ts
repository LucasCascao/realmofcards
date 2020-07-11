/* eslint-disable no-unused-vars */
import {TipoUsuario} from './tipo-usuario';
import { Status } from './status.model';
export class Usuario {
  id: number;
  codigo: string;
  password: string;
  rePassword: string;
  email: string;
  tipoUsuario: TipoUsuario;
  status: Status;
}
