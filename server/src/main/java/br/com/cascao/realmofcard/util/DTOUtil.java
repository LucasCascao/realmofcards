package br.com.cascao.realmofcard.util;


import br.com.cascao.realmofcard.domain.Resultado;
import br.com.cascao.realmofcard.dto.IDTO;
import br.com.cascao.realmofcard.util.validador.Util;

public class DTOUtil {
    public static Resultado tranfereParaDTO(Resultado resultado, IDTO dto){
        if(Util.isNotNull(resultado.getEntidades()))
            resultado.getEntidades().replaceAll( entidade -> dto.getDTO(entidade));
        return resultado;
    }
}
