package br.com.lucas.monolito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.monolito.model.Cliente;
import br.com.lucas.monolito.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Cliente> lisar() {
        return service.buscarTodos();
    }

    @RequestMapping(value = "/{nome:.+}", method = RequestMethod.GET)
    public List<Cliente> listarPorNome(@PathVariable String nome) {
        return service.buscarPorNome(nome);
    }
}
