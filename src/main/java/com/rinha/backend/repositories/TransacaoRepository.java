package com.rinha.backend.repositories;

import com.rinha.backend.execeptions.UnprocessableEntityException;
import com.rinha.backend.models.Transacao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TransacaoRepository {

    private final JdbcTemplate jdbcTemplate;

    public TransacaoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional()
    public int salvarTransacao(Transacao transacao, int limiteCliente, int clienteId) {
        String updateCliente =  "UPDATE clientes SET saldo = saldo - ?, limite = ? WHERE id = ?";
        if(transacao.getTipo().equals("c")) {
            updateCliente = "UPDATE clientes SET saldo = saldo + ?, limite = ? WHERE id = ?";
        }
        jdbcTemplate.update("INSERT INTO transacoes (valor, cliente_id, tipo, descricao, realizada_em) VALUES (?, ?, ?, ?,?); " +
                        updateCliente, transacao.getValor(), clienteId, transacao.getTipo(), transacao.getDescricao(), LocalDateTime.now(),
                transacao.getValor(), limiteCliente, clienteId);

        int saldoCliente = jdbcTemplate.queryForObject("SELECT saldo FROM clientes WHERE id = ?", Integer.class, clienteId);
        if (saldoCliente < -limiteCliente) {
            throw new UnprocessableEntityException();
        }
        return saldoCliente;
    }

    public Map<String, Object> buscarExtrato(Integer clienteId, int limite) {
        Integer saldoAtual = jdbcTemplate.queryForObject("SELECT saldo FROM clientes WHERE id = ?", Integer.class, clienteId);
        List<Transacao> ultimasTransacoes = jdbcTemplate.query("SELECT transacoes.valor, transacoes.tipo, transacoes.descricao, transacoes.realizada_em FROM transacoes WHERE cliente_id = ? ORDER BY id DESC LIMIT 10",
                (resultSet, rowNum) -> {
                    Transacao transacao = new Transacao();
                    transacao.setValor(resultSet.getInt("valor"));
                    transacao.setTipo(resultSet.getString("tipo"));
                    transacao.setDescricao(resultSet.getString("descricao"));
                    transacao.setRealizadaEm(resultSet.getTimestamp("realizada_em").toLocalDateTime());
                    return transacao;
                }, clienteId);
        Map<String, Object> saldo = new HashMap<>();
        saldo.put("total", saldoAtual);
        saldo.put("data_extrato", LocalDateTime.now());
        saldo.put("limite", limite);

        Map<String, Object> extrato = new HashMap<>();
        extrato.put("saldo", saldo);
        extrato.put("ultimas_transacoes", ultimasTransacoes);

        return extrato;
    }
}
