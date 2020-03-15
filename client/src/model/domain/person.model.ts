import {Telefone} from './telefone.model.js.js';
import {User} from "./user.model";

export class Person {
  id: number;
  nome: string;
  sobrenome: string;
  dataNascimento: Date;
  sexo: string;
  cpf: string;
  usuario: User;
}
