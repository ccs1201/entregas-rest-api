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
    BigDecimal expectedCommission;

    @BeforeAll
    private void init(){

        cliente.setId(7L);
        destinatario.setId(4L);

        entrega.setCliente(cliente);
        entrega.setDestinatario(destinatario);
        entrega.setPercentualComissao(10);

    }

    @Test
    @DisplayName("Testa Calculo da Comissão de Serviço Assertivo")
    void TestaComissaoServico() {

        entrega.setValorEntrega(new BigDecimal("435.99"));

        expectedCommission = new BigDecimal(43.60).setScale(2,RoundingMode.HALF_UP);

        entrega = service.save(entrega);

        Assertions.assertEquals(expectedCommission,entrega.getValorComissaoEntrega());

    }
/*
    private BigDecimal expectedCommission(){

        BigDecimal expectedResult = entrega.getValorEntrega();
        BigDecimal percentualComissao = new BigDecimal(entrega.getPercentualComissao()).divide(new BigDecimal("100"));
        expectedResult = expectedResult.multiply(percentualComissao).setScale(2, RoundingMode.HALF_UP);

        return expectedResult;
    }
    */
}