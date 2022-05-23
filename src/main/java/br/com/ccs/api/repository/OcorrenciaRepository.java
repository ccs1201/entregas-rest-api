package br.com.ccs.api.repository;

import br.com.ccs.api.domain.model.Ocorrencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

    Page<Ocorrencia> findByEntregaId(Long id, Pageable pageable);
}