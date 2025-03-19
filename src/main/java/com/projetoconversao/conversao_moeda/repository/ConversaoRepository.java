package com.projetoconversao.conversao_moeda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetoconversao.conversao_moeda.entidade.Conversao;

public interface ConversaoRepository extends JpaRepository<Conversao, Long> {
}

