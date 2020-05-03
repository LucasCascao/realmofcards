package br.com.cascao.realmofcard.util.validador;

import org.springframework.stereotype.Component;

@Component
public class ValidadorString implements IValidator {
    @Override
    public String validar(Object objeto, String campo) {
        return objeto == null || objeto.toString().trim().equals("")? "O campo " + campo + " � obrigat�rio(a)." : "";
    }
}
