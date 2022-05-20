package br.com.ccs.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ccs.api.domain.model.Entrega;
@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    @Override
    Page<Entrega> findAll(Pageable pageable);
}
