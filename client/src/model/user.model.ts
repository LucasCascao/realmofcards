import {Person} from './person.model';

export class User {
  id: number;
  username: string;
  password: string;
  email: string;
  tipoUsuario;
  ativo;
  pessoa: Person;
}
