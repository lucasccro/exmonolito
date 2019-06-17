package br.com.lucas.monolito.batch;

import org.springframework.batch.item.ItemProcessor;

import br.com.lucas.monolito.model.Cliente;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class ClienteProcessor implements ItemProcessor<Cliente, Cliente> {

    @Override
    public Cliente process(Cliente cliente) throws Exception {
        LocalDate ld = LocalDate.parse(cliente.getDtnascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Period period = Period.between(ld, LocalDate.now());
        cliente.setIdade(period.getYears());
        return cliente;
    }
}
