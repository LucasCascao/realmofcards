package br.edu.les.realmofcard.strategy.dashboard;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pedido;
import br.edu.les.realmofcard.domain.grafico.Dashboard;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.Util;
import br.edu.les.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosDashboard implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {
        StringBuilder msg = new StringBuilder();
        if(entidade instanceof Dashboard) {
            Dashboard dashboard = (Dashboard) entidade;
            if(Util.isNotNull(dashboard.getDataInicio())){
                if(dashboard.getDataInicio().getYear() < 2019){
                    msg.append("Data de inicio está abaixo da data permitida.");
                }
                if(dashboard.getDataInicio().getYear() > 2020 || (dashboard.getDataInicio().getYear() == 2020 && dashboard.getDataInicio().getMonthValue() > 6)){
                    msg.append("Data de inicio está maior que a data permitida.");
                }
            } else {
                msg.append(validadorString.validar(null, "data de inicio"));
            }
            if(Util.isNotNull(dashboard.getDataFim())){
                if(dashboard.getDataFim().getYear() < 2019){
                    msg.append("Data de fim está abaixo da data permitida.");
                }
                if(dashboard.getDataFim().getYear() > 2020 || (dashboard.getDataFim().getYear() == 2020 && dashboard.getDataFim().getMonthValue() > 6)){
                    msg.append("Data de fim está maior que a data permitida.");
                }
            } else {
                msg.append(validadorString.validar(null, "data de fim"));
            }
            if(msg.length() == 0 && dashboard.getDataInicio().isAfter(dashboard.getDataFim())){
                msg.append("Data de inicio é maior que a data de fim.");
            }
            if(Util.isNull(dashboard.getTipoGrafico())){
                msg.append("Está faltando tipo do gráfico.");
            }
        }
        return msg.toString();
    }
}
