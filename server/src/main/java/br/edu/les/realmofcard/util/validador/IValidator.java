package br.edu.les.realmofcard.util.validador;

import org.springframework.stereotype.Component;

@Component
public interface IValidator {
    public String validar(Object o, String fieldName);
}