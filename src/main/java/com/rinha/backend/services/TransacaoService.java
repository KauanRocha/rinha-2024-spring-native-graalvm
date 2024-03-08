package com.rinha.backend.services;

import com.rinha.backend.payloads.TransacaoRequest;
import com.rinha.backend.repositories.TransacaoRepository;

import java.util.HashMap;
import java.util.Map;

public class TransacaoService {

    private static  int[] limits = {0, 1, 2, 3, 4, 5};

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public Map<String, Integer> criatransacao(int id, TransacaoRequest request) {
        Map<String, Integer> reponse = new HashMap<>();
        reponse.put("saldo", transacaoRepository.salvarTransacao(request, limits[id], id));
        reponse.put("limite", limits[id]);
        return reponse;
    }

}
