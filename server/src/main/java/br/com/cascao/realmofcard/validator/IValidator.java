package br.com.cascao.realmofcard.validator;

import org.springframework.stereotype.Component;

@Component
public interface IValidator {
    public String validar(Object o, String fieldName);
}
