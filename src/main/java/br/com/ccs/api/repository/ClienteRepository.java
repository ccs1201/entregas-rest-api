package br.com.ccs.api.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ccs.api.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	Collection<Cliente> findByNome(String nome);
	Collection<Cliente> findByNomeContaining(String nome);
	Optional<Cliente> findByEmail(String email);

}
