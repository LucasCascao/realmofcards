import {Person} from './person.model';
import {TipoUsuario} from './tipo-usuario';
export class User {
  id: number;
  password: string;
  email: string;
  tipoUsuario: TipoUsuario;
  ativo: boolean;
}
