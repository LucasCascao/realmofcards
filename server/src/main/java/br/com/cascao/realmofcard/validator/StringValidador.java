package br.com.cascao.realmofcard.validator;

import org.springframework.stereotype.Component;

@Component
public class StringValidador implements IValidator{
    @Override
    public String validar(Object o, String s) {
        return o == null || o.toString().trim().equals("")? "O campo " + s + " é obrigatório(a)." : "";
    }
}
