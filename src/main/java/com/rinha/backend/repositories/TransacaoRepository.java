package com.rinha.backend.repositories;

import com.rinha.backend.models.Transacao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TransacaoRepository {

    private final JdbcTemplate jdbcTemplate;

    public TransacaoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    public Map<String, Integer> salvarTransacao(Transacao transacao, int limiteCliente) {
        int saldoCliente = jdbcTemplate.queryForObject("SELECT saldo FROM clientes WHERE id = ?", Integer.class, transacao.getClienteId());

        if (saldoCliente - transacao.getValor() < -limiteCliente) {
            throw new RuntimeException("Transação não permitida: ultrapassaria o limite do cliente");
        }
        jdbcTemplate.update("INSERT INTO transacoes (valor, cliente_id, tipo, descricao) VALUES (?, ?, ?, ?); " +
                        "UPDATE clientes SET saldo = saldo - ?, limite = ? WHERE id = ?", transacao.getValor(), transacao.getClienteId(), transacao.getTipo(), transacao.getDescricao(),
                transacao.getValor(), limiteCliente, transacao.getClienteId());

        Map<String, Integer> saldoLimite = new HashMap<>();
        saldoLimite.put("limite", limiteCliente);
        saldoLimite.put("saldo", saldoCliente - transacao.getValor());

        return saldoLimite;
    }
}
