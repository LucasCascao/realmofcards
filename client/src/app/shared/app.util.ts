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
}
