package br.com.lucas.monolito.service;

import java.util.List;

import br.com.lucas.monolito.model.Cliente;

public interface ClienteService {

    List<Cliente> buscarTodos();

    List<Cliente> buscarPorNome(String nome);
}
