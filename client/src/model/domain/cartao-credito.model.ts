import { Bandeira } from './bandeira.model';
import { Pessoa } from './person.model';

export class CartaoCredito {
    id: number;
    numero: string;
    codigoSeguranca: string;
    preferido: boolean;
    bandeira: Bandeira;
    pessoa: Pessoa;
}
