package com.rinha.backend.services;

import com.rinha.backend.execeptions.NotFoundException;
import com.rinha.backend.execeptions.UnprocessableEntityException;
import com.rinha.backend.models.Transacao;
import com.rinha.backend.repositories.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TransacaoService {
    private static final int[] limits = {0, 100000, 80000, 1000000, 10000000, 500000};
    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public Map<String, Integer> criaTransacao(int id, Transacao request) {
        checkCliente(id);
        Map<String, Integer> reponse = new HashMap<>();
        reponse.put("saldo", transacaoRepository.salvarTransacao(request, limits[id], id));
        reponse.put("limite", limits[id]);
        return reponse;
    }

    public Map<String, Object> buscarExtrato(int clienteId) {
        checkCliente(clienteId);
        Map<String, Object> extrato = transacaoRepository.buscarExtrato(clienteId);
        extrato.put("limte", limits[clienteId]);

        return extrato;
    }

    public void checkCliente(int clienteId) {
        if(clienteId > 5) {
            throw new NotFoundException();
        }
    }

    public static void validarTransacao(Transacao transacao) {
        if (transacao.getValor() == null || transacao.getValor() <= 0 ||
                !"c".equals(transacao.getTipo()) && !"d".equals(transacao.getTipo()) ||
                transacao.getDescricao() == null || transacao.getDescricao().isEmpty() || transacao.getDescricao().length() > 10) {
            throw new UnprocessableEntityException();
        }
    }

}
