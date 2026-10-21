package org.acme.services;

import org.acme.modules.Cliente;

import java.math.BigDecimal;

public class PagamentoService {
    public void procPagamento(Cliente cliente){
        if(cliente == null){
            throw new IllegalArgumentException("Cliente inválido!");
        }

        BigDecimal valor = cliente.getPlano().mensalidade();

        System.out.println("Processando pagamento...");
        System.out.printf("Aluno: %s\nValor: %.2f\n", cliente.getNome(), valor);
        System.out.println("Pagamento concluído");
    }
}
