export class Util {

  getMensagensSeparadas(mensagemRecebida?: string): string {
    let msgsSeparadas = '';
    mensagemRecebida?.split('.').forEach( msg => {
      msgsSeparadas = msgsSeparadas + `${msg}\n`;
    });
    return msgsSeparadas;
  }
}
