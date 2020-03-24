import { Cidade } from './cidade.model';
import { Person } from './person.model';

export class Endereco {
    id: number;
    logradouro: string;
    numero: string;
    bairro: string;
    cep: string;
    complemento: string;
    cidade: Cidade;
    pessoa: Person;
}