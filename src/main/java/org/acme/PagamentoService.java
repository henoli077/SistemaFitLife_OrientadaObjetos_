package org.acme;

public class PagamentoService {
    public void procPagamento(Aluno aluno){
        if(aluno == null){
            throw new IllegalArgumentException("Aluno inválido!");
        }

        double valor = aluno.plano().mensalidade();

        System.out.println("Processando pagamento...");
        System.out.printf("Aluno: %s\nValor: %.2f\n", aluno.nome(), valor);
        System.out.println("Pagamento concluído");
    }
}
