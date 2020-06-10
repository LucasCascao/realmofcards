import { Serie } from './serie';

export class Dashboard {
  title: string;
  series: Array<Serie>;
  dataInicio: Date;
  dataFim: Date;
  tipoGrafico: string;
}
