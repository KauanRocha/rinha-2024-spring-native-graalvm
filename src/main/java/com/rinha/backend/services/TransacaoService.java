package com.rinha.backend.services;

import com.rinha.backend.repositories.TransacaoRepository;

public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }


}
