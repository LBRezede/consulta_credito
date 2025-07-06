package com.credito.creditmanagement.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.credito.creditmanagement.model.Credito;
import com.credito.creditmanagement.repository.CreditoRepository;

@Service
public class CreditoService {

    private final CreditoRepository creditoRepository;

    public CreditoService(CreditoRepository creditoRepository) {
        this.creditoRepository = creditoRepository;
    }

    public Credito findByNumeroCredito(String numeroCredito) {
        return creditoRepository.findByNumeroCredito(numeroCredito);
    }

    public List<Credito> findByNumeroNfse(String numeroNfse) {
        return creditoRepository.findByNumeroNfse(numeroNfse);
    }
}
