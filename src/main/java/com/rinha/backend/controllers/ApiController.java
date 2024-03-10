package com.rinha.backend.controllers;

import com.rinha.backend.models.Transacao;
import com.rinha.backend.services.TransacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
public class ApiController {

    private final TransacaoService transacaoService;

    ApiController (TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/clientes/{clienteId}/transacoes")
    public Map<String, Integer> criaTransacao(@PathVariable(name = "clienteId") int clienteId, @RequestBody Transacao transacaoRequest) {
        TransacaoService.validarTransacao(transacaoRequest);
        return transacaoService.criaTransacao(clienteId, transacaoRequest);
    }

    @GetMapping("/clientes/{clienteId}/extrato")
    public Map<String, Object> buscaExtrato(@PathVariable(name = "clienteId") int clienteId) {
        return transacaoService.buscarExtrato(clienteId);
    }
}
