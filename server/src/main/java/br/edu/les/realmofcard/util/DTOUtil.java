package br.edu.les.realmofcard.util;


import br.edu.les.realmofcard.domain.Resultado;
import br.edu.les.realmofcard.dto.IDTO;

public class DTOUtil {
    public static Resultado tranfereParaDTO(Resultado resultado, IDTO dto){
        if(Util.isNotNull(resultado.getEntidades()))
            resultado.getEntidades().replaceAll( entidade -> dto.parseEntityToDTO(entidade));
        return resultado;
    }
}
