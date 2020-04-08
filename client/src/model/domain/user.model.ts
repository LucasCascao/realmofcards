import {Person} from './person.model';
import {TipoUsuario} from './tipo-usuario';
import { Status } from './status.model';
export class User {
  id: number;
  password: string;
  rePassword: string;
  email: string;
  tipoUsuario: TipoUsuario;
  status: Status;
}
