package org.acme.modules.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.modules.models.Cliente;

@ApplicationScoped
public class PagamentoService {
    public void procPagamento(Cliente cliente){
        if(cliente == null){
            throw new IllegalArgumentException("Cliente inválido!");
        }

        if(cliente.getPlano() == null){
            throw new IllegalStateException("O cliente não possui um plano associado!");
        }

        double valor = cliente.getPlano().calcularMensalidade();

        System.out.println("Processando pagamento...");
        System.out.printf("Aluno: %s\nValor: %.2f\n", cliente.getNome(), valor);
        System.out.println("Pagamento concluído");
    }
}
