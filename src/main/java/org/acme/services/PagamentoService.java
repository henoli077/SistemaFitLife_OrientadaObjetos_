package org.acme.services;

import org.acme.modules.Cliente;

public class PagamentoService {
    public void procPagamento(Cliente cliente){
        if(cliente == null){
            throw new IllegalArgumentException("Cliente inválido!");
        }

        double valor = cliente.getPlano().mensalidade();

        System.out.println("Processando pagamento...");
        System.out.printf("Aluno: %s\nValor: %.2f\n", cliente.getNome(), valor);
        System.out.println("Pagamento concluído");
    }
}
