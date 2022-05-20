package br.com.ccs.api.repository;

import br.com.ccs.api.domain.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	Collection<Cliente> findByNome(String nome);
	Collection<Cliente> findByNomeContaining(String nome);
	Optional<Cliente> findByEmail(String email);
	@Override
	Page<Cliente> findAll(Pageable pageable);
}
