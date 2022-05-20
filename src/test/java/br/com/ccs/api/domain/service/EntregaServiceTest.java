package br.com.ccs.api.domain.service;

import br.com.ccs.api.domain.model.Cliente;
import br.com.ccs.api.domain.model.Destinatario;
import br.com.ccs.api.domain.model.Entrega;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EntregaServiceTest {

    @Autowired
    EntregaService service;

    Entrega entrega = new Entrega();
    Cliente cliente = new Cliente();
    Destinatario destinatario = new Destinatario();

    @BeforeAll
    private void init(){

        service.setPercentualComissao(10);

        cliente.setId(1L);
        destinatario.setId(1L);

        entrega.setCliente(cliente);
        entrega.setDestinatario(destinatario);
        entrega.setValorEntrega(new BigDecimal("435.99"));

        entrega = service.save(entrega);
    }

    @Test
    @DisplayName("Testa Calculo da Comissão de Serviço Assertivo")
    void TestaComissaoServico() {



        Assertions.assertEquals(expectedCommission(),entrega.getComissaoServico());

    }

    private BigDecimal expectedCommission(){

        BigDecimal expectedResult = entrega.getValorEntrega();
        BigDecimal percentualComissao = new BigDecimal(service.getPercentualComissao()).divide(new BigDecimal("100"));
        expectedResult = expectedResult.multiply(percentualComissao).setScale(2, RoundingMode.HALF_UP);

        return expectedResult;
    }
}