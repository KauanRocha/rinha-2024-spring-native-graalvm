package com.rinha.backend.repositories;

import com.rinha.backend.execeptions.UnprocessableEntityException;
import com.rinha.backend.models.Transacao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TransacaoRepository {

    private final JdbcTemplate jdbcTemplate;

    public TransacaoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int salvarTransacao(Transacao transacao, int limiteCliente, int clienteId) {
        String updateCliente =  "UPDATE clientes SET saldo = saldo - ?, limite = ? WHERE id = ?";
        if(transacao.getTipo() == "d") {
            updateCliente = "UPDATE clientes SET saldo = saldo + ?, limite = ? WHERE id = ?";
        }
        jdbcTemplate.update("INSERT INTO transacoes (valor, cliente_id, tipo, descricao, realizada_em) VALUES (?, ?, ?, ?); " +
                        updateCliente, transacao.getValor(), clienteId, transacao.getTipo(), transacao.getDescricao(), Instant.now(),
                transacao.getValor(), limiteCliente, clienteId);

        int saldoCliente = jdbcTemplate.queryForObject("SELECT saldo FROM clientes WHERE id = ? FOR UPDATE", Integer.class, clienteId);
        if (saldoCliente < limiteCliente) {
            throw new UnprocessableEntityException();
        }
        return saldoCliente;
    }

    public Map<String, Object> buscarExtrato(Integer clienteId) {
        Integer saldo = jdbcTemplate.queryForObject("SELECT saldo FROM clientes WHERE id = ?", Integer.class, clienteId);
        List<Transacao> ultimasTransacoes = jdbcTemplate.queryForList("SELECT * FROM transacao WHERE clienteId = ? ORDER BY id DESC LIMIT 10", Transacao.class, clienteId);

        Map<String, Object> extrato = new HashMap<>();
        extrato.put("saldo", saldo);
        extrato.put("ultimas_transacoes", ultimasTransacoes);

        return extrato;
    }
}
