/* eslint-disable no-unused-vars */
import {Telefone} from './telefone.model.js';
import {Usuario} from "./usuario.model";

export class Pessoa {
  id: number;
  nome: string;
  sobrenome: string;
  dataNascimento: Date;
  sexo: string;
  cpf: string;
  usuario: Usuario;
  telefones: Telefone[];
}
