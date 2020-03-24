package br.com.cascao.realmofcard.util.validator;

import org.springframework.stereotype.Component;

@Component
public class StringValidador implements IValidator {
    @Override
    public String validar(Object objeto, String campo) {
        return objeto == null || objeto.toString().trim().equals("")? "O campo " + campo + " é obrigatório(a)." : "";
    }
}
