package com.rinha.backend.repositories;

import com.rinha.backend.payloads.TransacaoRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.Instant;

@Repository
public class TransacaoRepository {

    private final JdbcTemplate jdbcTemplate;

    public TransacaoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int salvarTransacao(TransacaoRequest transacao, int limiteCliente, int clienteId) {
        String updateCliente =  "UPDATE clientes SET saldo = saldo - ?, limite = ? WHERE id = ?";
        if(transacao.getTipo() == "d") {
            updateCliente = "UPDATE clientes SET saldo = saldo + ?, limite = ? WHERE id = ?";
        }
        jdbcTemplate.update("INSERT INTO transacoes (valor, cliente_id, tipo, descricao, realizada_em) VALUES (?, ?, ?, ?); " +
                        updateCliente, transacao.getValor(), clienteId, transacao.getTipo(), transacao.getDescricao(), Instant.now(),
                transacao.getValor(), limiteCliente, clienteId);

        int saldoCliente = jdbcTemplate.queryForObject("SELECT saldo FROM clientes WHERE id = ? FOR UPDATE", Integer.class, clienteId);
        if (saldoCliente < limiteCliente) {
            throw new RuntimeException("Transação não permitida: ultrapassaria o limite do cliente");
        }
        return saldoCliente;
    }
}
