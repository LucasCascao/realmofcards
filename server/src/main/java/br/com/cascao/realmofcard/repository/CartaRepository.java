package br.com.cascao.realmofcard.repository;

import br.com.cascao.realmofcard.domain.CardBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaRepository extends JpaRepository<CardBean, Long> {
}
