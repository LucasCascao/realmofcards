package br.edu.les.realmofcard.util.validador;

import org.springframework.stereotype.Component;

@Component
public class DoubleValidador implements IValidator {
    @Override
    public String validar(Object objeto, String campo) {
            return objeto == null || objeto.toString() == ""
                    || (objeto instanceof Double && Double.parseDouble(objeto.toString()) == 0.0) ? "O campo " + campo + " é obrigatório(a)." : "";
    }
}
