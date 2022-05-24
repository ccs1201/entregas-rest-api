package br.com.ccs.api.controller;

import br.com.ccs.api.domain.model.entity.Cliente;
import br.com.ccs.api.domain.model.entity.Destinatario;
import br.com.ccs.api.domain.model.entity.Entrega;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EntregaControllerTest {

    @Autowired
    EntregaController controller;

    Entrega entrega = new Entrega();
    Cliente cliente = new Cliente();
    Destinatario destinatario = new Destinatario();

    @Test
    @DisplayName("Testando salvar ")
    void save() {
    }

    @Test
    @DisplayName("Testando deletar ")
    void delete() {
    }

    @Test
    @DisplayName("Testando atualizar ")
    void update() {
    }

    @Test
    @DisplayName("Testando geall")
    void getAll() {
    }

    @Test
    @DisplayName("Testando findbyId")
    void findbyId() {
    }
}