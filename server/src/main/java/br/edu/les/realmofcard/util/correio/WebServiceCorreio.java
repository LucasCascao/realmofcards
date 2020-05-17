package br.edu.les.realmofcard.util.correio;

import org.springframework.beans.factory.annotation.Value;
import org.tempuri.CResultado;
import org.tempuri.CServico;
import org.tempuri.CalcPrecoPrazoWSSoapProxy;

import br.edu.les.realmofcard.dto.CorreioDTO;

import java.math.BigDecimal;

public class WebServiceCorreio {

    @Value("${context.imagem-path}")
    static String teste;

    static String nCdEmpresa = "";
    static String sDsSenha = "";
    static String nCdServico = "40010"; //sedex
    static String sCepOrigem = "08665410";
    static String sCepDestino;
    static String nVlPeso = "0.4";
    static int nCdFormato = 1; // caixa
    static BigDecimal nVlComprimento = new BigDecimal(16);
    static BigDecimal nVlAltura = new BigDecimal(6);
    static BigDecimal nVlLargura = new BigDecimal(16);
    static BigDecimal nVlDiametro = new BigDecimal(0);
    static String sCdMaoPropria = "S";
    static BigDecimal nVlValorDeclarado = new BigDecimal(150);
    static String sCdAvisoRecebimento = "S";


    public static CorreioDTO calculaPrecoPrazo(String cepDestino) {
        CorreioDTO correioDTO = null;
        try {
            CalcPrecoPrazoWSSoapProxy service = new CalcPrecoPrazoWSSoapProxy();
            CResultado retornoCorreios = service.getCalcPrecoPrazoWSSoap().calcPrecoPrazo(
                    nCdEmpresa,
                    sDsSenha,
                    nCdServico,
                    sCepOrigem,
                    cepDestino,
                    nVlPeso,
                    nCdFormato,
                    nVlComprimento,
                    nVlAltura,
                    nVlLargura,
                    nVlDiametro,
                    sCdMaoPropria,
                    nVlValorDeclarado,
                    sCdAvisoRecebimento);

            CServico[] cservico = ((CResultado) retornoCorreios).getServicos();

            correioDTO = new CorreioDTO();
            correioDTO.setValorCusto(Double.valueOf(cservico[0].getValor().replace(",", ".")));
            correioDTO.setQuantidadeDiasEntrega(Integer.valueOf(cservico[0].getPrazoEntrega()));

            return correioDTO;

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return correioDTO;
    }
}
