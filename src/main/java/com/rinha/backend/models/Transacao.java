package com.rinha.backend.models;


import org.springframework.stereotype.Component;

@Component
public class Transacao {

    private Integer valor;

    private String tipo;

    private String descricao;

    private Long realizadaEm;

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

    public Long getRealizadaEm() {
        return realizadaEm;
    }

    public void setRealizadaEm(Long realizadaEm) {
        this.realizadaEm = realizadaEm;
    }
}
