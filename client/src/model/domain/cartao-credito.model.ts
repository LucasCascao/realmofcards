import { Bandeira } from './bandeira.model';
import { Person } from './person.model';

export class CartaoCredito {
    id: number;
    numero: string;
    codigoSeguranca: string;
    bandeira: Bandeira;
    pessoa: Person;
}