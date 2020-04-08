import {Telefone} from './telefone.model.js';
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
