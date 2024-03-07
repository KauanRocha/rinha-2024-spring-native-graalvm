package com.rinha.backend.repositories;

import com.rinha.backend.models.Transacao;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
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

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int salvarTransacao(Transacao transacao, int limiteCliente) {
        jdbcTemplate.update("INSERT INTO transacoes (valor, cliente_id, tipo, descricao) VALUES (?, ?, ?, ?); " +
                        "UPDATE clientes SET saldo = saldo - ?, limite = ? WHERE id = ?", transacao.getValor(), transacao.getClienteId(), transacao.getTipo(), transacao.getDescricao(),
                transacao.getValor(), limiteCliente, transacao.getClienteId());

        int saldoCliente = jdbcTemplate.queryForObject("SELECT saldo FROM clientes WHERE id = ? FOR UPDATE", Integer.class, transacao.getClienteId());
        if (saldoCliente < limiteCliente) {
            throw new RuntimeException("Transação não permitida: ultrapassaria o limite do cliente");
        }
        return saldoCliente;
    }
}
