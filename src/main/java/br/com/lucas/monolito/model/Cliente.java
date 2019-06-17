package br.com.lucas.monolito.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente {

    private String nome;
    private String sobrenome;
    private String dtnascimento;
    private Integer idade;

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDtnascimento() {
        return dtnascimento;
    }

    public Integer getIdade() {
        return idade;
    }

    public Cliente setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Cliente setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
        return this;
    }

    public Cliente setDtnascimento(String dtnascimento) {
        this.dtnascimento = dtnascimento;
        return this;
    }

    public Cliente setIdade(Integer idade) {
        this.idade = idade;
        return this;
    }

    public static class ClienteRowMapper implements RowMapper<Cliente> {

        @Override
        public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Cliente()
                    .setNome(resultSet.getString("nome"))
                    .setSobrenome(resultSet.getString("sobrenome"))
                    .setDtnascimento(resultSet.getString("dtnascimento"))
                    .setIdade(resultSet.getInt("idade"))
                    ;
        }
    }
}
