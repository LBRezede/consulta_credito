package com.credito.creditmanagement.integration;

import com.credito.creditmanagement.model.Credito;
import com.credito.creditmanagement.repository.CreditoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1)
public class CreditoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CreditoRepository creditoRepository;

    @Test
    void buscarPorNumeroNfse_DeveRetornarCreditos() throws Exception {
        // Arrange
        Credito credito = criarEPersistirCredito();

        // Act & Assert
        mockMvc.perform(get("/api/creditos/{numeroNfse}", credito.getNumeroNfse()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numeroCredito").value(credito.getNumeroCredito()))
                .andExpect(jsonPath("$[0].numeroNfse").value(credito.getNumeroNfse()));
    }

    private Credito criarEPersistirCredito() {
        Credito credito = new Credito();
        credito.setNumeroCredito("123456");
        credito.setNumeroNfse("7891011");
        credito.setDataConstituicao(LocalDate.now());
        credito.setValorIssqn(new BigDecimal("1500.75"));
        credito.setTipoCredito("ISSQN");
        credito.setSimplesNacional(true);
        credito.setAliquota(new BigDecimal("5.0"));
        credito.setValorFaturado(new BigDecimal("30000.00"));
        credito.setValorDeducao(new BigDecimal("5000.00"));
        credito.setBaseCalculo(new BigDecimal("25000.00"));
        return creditoRepository.save(credito);
    }
}