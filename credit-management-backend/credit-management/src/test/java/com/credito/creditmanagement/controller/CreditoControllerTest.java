package com.credito.creditmanagement.controller;

import com.credito.creditmanagement.model.Credito;
import com.credito.creditmanagement.repository.CreditoRepository;
import com.credito.creditmanagement.service.KafkaProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreditoControllerTest {

    @Mock
    private CreditoRepository repository;

    @Mock
    private KafkaProducerService kafkaService;

    private CreditoController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new CreditoController(repository, kafkaService);
    }

    @Test
    void buscarPorNumeroNfse_QuandoEncontrado_RetornaCreditos() {
        // Arrange
        String numeroNfse = "7891011";
        Credito credito = criarCreditoTeste();
        when(repository.findByNumeroNfse(numeroNfse)).thenReturn(Arrays.asList(credito));

        // Act
        ResponseEntity<List<Credito>> response = controller.buscarPorNumeroNfse(numeroNfse);

        // Assert
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(kafkaService).enviarMensagem(contains(numeroNfse));
    }

    @Test
    void buscarPorNumeroNfse_QuandoNaoEncontrado_RetornaNotFound() {
        // Arrange
        String numeroNfse = "nao-existe";
        when(repository.findByNumeroNfse(numeroNfse)).thenReturn(Arrays.asList());

        // Act
        ResponseEntity<List<Credito>> response = controller.buscarPorNumeroNfse(numeroNfse);

        // Assert
        assertTrue(response.getStatusCode().is4xxClientError());
        verify(kafkaService, never()).enviarMensagem(any());
    }

    @Test
    void buscarPorNumeroCredito_QuandoEncontrado_RetornaCredito() {
        // Arrange
        String numeroCredito = "123456";
        Credito credito = criarCreditoTeste();
        when(repository.findByNumeroCredito(numeroCredito)).thenReturn(credito);

        // Act
        ResponseEntity<Credito> response = controller.buscarPorNumeroCredito(numeroCredito);

        // Assert
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(numeroCredito, response.getBody().getNumeroCredito());
        verify(kafkaService).enviarMensagem(contains(numeroCredito));
    }

    @Test
    void buscarPorNumeroCredito_QuandoNaoEncontrado_RetornaNotFound() {
        // Arrange
        String numeroCredito = "nao-existe";
        when(repository.findByNumeroCredito(numeroCredito)).thenReturn(null);

        // Act
        ResponseEntity<Credito> response = controller.buscarPorNumeroCredito(numeroCredito);

        // Assert
        assertTrue(response.getStatusCode().is4xxClientError());
        verify(kafkaService, never()).enviarMensagem(any());
    }

    private Credito criarCreditoTeste() {
        Credito credito = new Credito();
        credito.setId(1L);
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
        return credito;
    }
}