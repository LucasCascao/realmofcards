package br.com.cascao.realmofcard.dto;

import br.com.cascao.realmofcard.domain.EntidadeDominio;

public interface IDTO {
    public EntidadeDominio getDTO(EntidadeDominio dominio);
    public EntidadeDominio getEntidade(IDTO dto);
}
