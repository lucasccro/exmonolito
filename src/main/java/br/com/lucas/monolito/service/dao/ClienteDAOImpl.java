package br.com.lucas.monolito.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.lucas.monolito.model.Cliente;

import java.util.List;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Cliente> findAll() {
        return jdbcTemplate
                .query("SELECT nome, sobrenome, dtnascimento, idade FROM CLIENTES",
                        new BeanPropertyRowMapper(Cliente.class));
    }

    @Override
    public List<Cliente> findByName(String nome) {
        String nUpper = "%" + nome.toUpperCase() + "%";
        return jdbcTemplate
                .query("SELECT nome, sobrenome, dtnascimento, idade " +
                        "FROM CLIENTES " +
                        "WHERE upper(nome) LIKE ? " +
                        "OR upper(sobrenome) LIKE ? ",
                        new Object[]{nUpper, nUpper}, new BeanPropertyRowMapper(Cliente.class));
    }
}
