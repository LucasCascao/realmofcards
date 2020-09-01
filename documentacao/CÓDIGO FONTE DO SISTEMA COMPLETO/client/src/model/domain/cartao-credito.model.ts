import { Bandeira } from './bandeira.model';
import { Pessoa } from './pessoa.model';
import { Status } from './status.model';

export class CartaoCredito {
  id: number;
  numero: string;
  titularNome: string;
  vencimentoMes: string;
  vencimentoAno: string;
  codigoSeguranca: string;
  cpfTitular: string;
  preferido: boolean;
  bandeira: Bandeira;
  pessoa: Pessoa;
  status: Status;
}
