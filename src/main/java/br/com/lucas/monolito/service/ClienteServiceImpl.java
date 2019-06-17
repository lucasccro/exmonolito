package br.com.lucas.monolito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucas.monolito.model.Cliente;
import br.com.lucas.monolito.service.dao.ClienteDAO;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDAO dao;

    @Override
    public List<Cliente> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public List<Cliente> buscarPorNome(String nome) {
        return dao.findByName(nome);
    }
}
