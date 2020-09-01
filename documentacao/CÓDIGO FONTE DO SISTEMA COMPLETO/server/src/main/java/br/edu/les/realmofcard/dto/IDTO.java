package br.edu.les.realmofcard.dto;

import br.edu.les.realmofcard.domain.EntidadeDominio;

public interface IDTO {
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio);
    public EntidadeDominio parseDTOToEntity(IDTO dto);
}
