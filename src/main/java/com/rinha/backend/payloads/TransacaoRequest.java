package com.rinha.backend.payloads;

import com.rinha.backend.models.Transacao;


public class TransacaoRequest {

    private Integer valor;

    private String tipo;

    private String descricao;

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Transacao builder() {
        var t = new Transacao();
        t.setValor(valor);
        t.setTipo(tipo);
        t.setDescricao(descricao);
        return t;
    }
}