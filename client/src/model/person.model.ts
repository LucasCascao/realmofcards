import {Telefone} from './telefone.model.js';

export class Person {
  id: number;
  username: string;
  password: string;
  nome: string;
  sobrenome: string;
  dataNascimento: Date;
  sexo: string;
  cpf: string;
  email: string;
  telefone: Telefone[];
}
