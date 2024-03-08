package com.rinha.backend.controllers;

import com.rinha.backend.payloads.TransacaoRequest;
import com.rinha.backend.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiController {

    private final TransacaoService transacaoService;

    @Autowired
    ApiController (TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/clientes/{id}/transacoes")
    public Map<String, Integer> criaTransacao(@PathVariable(name = "id") int clienteId, @RequestBody TransacaoRequest transacaoRequest) {
        return transacaoService.criatransacao(clienteId, transacaoRequest);
    }

    @GetMapping("/clientes/{id}/extrato")
    public ExtratoResponse buscaExtrato(@PathVariable(name = "id") int clienteId) {
        return new ExtratoResponse(transacaoService.buscaExtrato(clienteId));
    }
}
