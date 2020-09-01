// eslint-disable-next-line no-unused-vars
import { Carta } from 'src/model/domain/carta.model';

/* eslint-disable no-unused-vars */
export class Util {

  getMensagensSeparadas(mensagemRecebida?: string): string {
    let msgsSeparadas = '';
    mensagemRecebida?.split('.').forEach( msg => {
      msgsSeparadas = msgsSeparadas + `${msg}\n`;
    });
    return msgsSeparadas;
  }

  getMensagensSeparadas2(mensagemRecebida?: string): string[] {
    let mensagemSeparadas: string[] = mensagemRecebida?.split('.');
    mensagemSeparadas.pop();
    return mensagemSeparadas;
  }

  isNull(objeto: any): boolean {
    return objeto === null || objeto === undefined;
  }

  isNotNull(objeto: any): boolean {
    return objeto != null || objeto != undefined;
  }

  formatarDataJSON(data: string): Date {
    if (data !== undefined && data != null && data !== '') {
      const dia = data.substr(0, 2);
      const mes = data.substr(2, 2);
      const ano = data.substr(4, 4);
      if (this.validaDigito(dia, 2, 31)
        && this.validaDigito(mes, 2, 12)
        && this.validaDigito(ano, 4, 2020)) {
        return new Date(ano + '-' + mes + '-' + dia);
      }
    }
    return null;
  }

  validaDigito(digito: string, quantidade: number, maximo: number): boolean {
    const digitoConvertido = Number.parseInt(digito, 10);
    return digitoConvertido > 0 && digitoConvertido <= maximo ? true : false;
  }

  formatarDataComum(date: Date): string {
    const data = date.toString().split('-');
    const dia = data.pop();
    const mes = data.pop();
    const ano = data.pop();
    return dia + mes + ano;
  }

  calculaValorPreco = (carta: Carta): number => (carta?.valorCompra + (carta?.valorCompra * (carta?.grupoPrecificacao?.valor / 100)));
}
