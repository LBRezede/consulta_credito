package com.credito.creditmanagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.KafkaException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credito.creditmanagement.model.Credito;
import com.credito.creditmanagement.repository.CreditoRepository;
import com.credito.creditmanagement.service.KafkaProducerService;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {
    private static final Logger logger = LoggerFactory.getLogger(CreditoController.class);
    private final CreditoRepository repository;
    private final KafkaProducerService kafkaService;

    public CreditoController(CreditoRepository repository, KafkaProducerService kafkaService) {
        this.repository = repository;
        this.kafkaService = kafkaService;
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<Credito> buscarPorNumeroCredito(@PathVariable String numeroCredito) {
        try {
            Credito credito = repository.findByNumeroCredito(numeroCredito);
            if (credito == null) {
                return ResponseEntity.notFound().build();
            }
            try {
                kafkaService.enviarMensagem("Consulta de crédito: " + numeroCredito);
            } catch (KafkaException e) {
                logger.error("Erro ao enviar mensagem para o Kafka: {}", e.getMessage());
                // Continue with the response even if Kafka fails
            }
            return ResponseEntity.ok(credito);
        } catch (Exception e) {
            logger.error("Erro ao buscar crédito: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<Credito>> buscarPorNumeroNfse(@PathVariable String numeroNfse) {
        try {
            List<Credito> creditos = repository.findByNumeroNfse(numeroNfse);
            if (creditos.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            try {
                kafkaService.enviarMensagem("Consulta de NFSe: " + numeroNfse);
            } catch (KafkaException e) {
                logger.error("Erro ao enviar mensagem para o Kafka: {}", e.getMessage());
                // Continue with the response even if Kafka fails
            }
            return ResponseEntity.ok(creditos);
        } catch (Exception e) {
            logger.error("Erro ao buscar NFSe: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
