package br.com.cascao.realmofcard.dto;

import br.com.cascao.realmofcard.domain.EntidadeDominio;

public interface IDTO {
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio);
    public EntidadeDominio parseDTOToEntity(IDTO dto);
}
