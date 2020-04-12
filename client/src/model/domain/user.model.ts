import {Pessoa} from './person.model';
import {TipoUsuario} from './tipo-usuario';
import { Status } from './status.model';
export class Usuario {
  id: number;
  password: string;
  rePassword: string;
  email: string;
  tipoUsuario: TipoUsuario;
  status: Status;
}
