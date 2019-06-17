package br.com.lucas.monolito.service.dao;

import java.util.List;

import br.com.lucas.monolito.model.Cliente;

public interface ClienteDAO {

    List<Cliente> findAll();

    List<Cliente> findByName(String nome);
}
