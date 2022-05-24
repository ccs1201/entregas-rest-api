package br.com.ccs.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ccs.api.domain.model.entity.Destinatario;

@Repository
public interface DestinatarioRepository extends JpaRepository<Destinatario, Long> {

    Page<Destinatario> findAll(Pageable pageable);

    Page<Destinatario> findByNomeContaining(String nome, Pageable pageable);

}
