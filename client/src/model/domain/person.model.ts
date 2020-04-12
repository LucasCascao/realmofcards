import {Telefone} from './telefone.model.js';
import {Usuario} from "./user.model";

export class Pessoa {
  id: number;
  nome: string;
  sobrenome: string;
  dataNascimento: Date;
  sexo: string;
  cpf: string;
  usuario: Usuario;
}
