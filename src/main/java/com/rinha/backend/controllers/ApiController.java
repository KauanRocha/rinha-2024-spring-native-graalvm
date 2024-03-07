package com.rinha.backend.controllers;

import com.rinha.backend.payloads.TransacaoRequest;
import com.rinha.backend.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class ApiController {

    private final TransacaoService transacaoService;

    @Autowired
    ApiController (TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/clientes/{id}/transacoes")
    public HashMap<String, Object> criaTransacao(@PathVariable(name = "id") int clienteId, @RequestBody TransacaoRequest transacaoRequest) {
        return new TransacoesResponse(transacaoService.criaTransacoes(clienteId, transacaoRequest));
    }

    @GetMapping("/clientes/{id}/extrato")
    public ExtratoResponse buscaExtrato(@PathVariable(name = "id") int clienteId) {
        return new ExtratoResponse(transacaoService.buscaExtrato(clienteId));
    }
}
