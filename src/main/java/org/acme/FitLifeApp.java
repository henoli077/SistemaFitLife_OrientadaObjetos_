package org.acme;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Scanner;
import org.acme.modules.models.Cliente;
import org.acme.modules.models.Plano;
import org.acme.modules.models.Treino;
import org.acme.modules.service.ClienteService;
import org.acme.modules.service.PagamentoService;
import org.acme.modules.service.PlanoService;
import org.acme.modules.service.TreinoService;

@QuarkusMain
public class FitLifeApp implements QuarkusApplication {

    @Inject
    ClienteService clienteService;

    @Inject
    PlanoService planoService;

    @Inject
    TreinoService treinoService;

    @Inject
    PagamentoService pagamentoService;

    private final Scanner scanner = new Scanner(System.in);

    public static void main(String... args) {
        Quarkus.run(FitLifeApp.class, args);
    }

    @Override
    public int run(String... args) {
        int opcao = -1;

        do {
            exibirMenu();

            try {
                opcao = lerInteiro("Escolha uma opção: ");

                switch (opcao) {
                    case 1 -> cadastrarCliente();
                    case 2 -> listarClientes();
                    case 3 -> listarPlanos();
                    case 4 -> associarPlano();
                    case 5 -> cadastrarTreino();
                    case 6 -> listarTreinosDoCliente();
                    case 7 -> processarPagamento();
                    case 0 -> System.out.println("Encerrando o sistema...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);

        return 0;
    }

    private void exibirMenu() {
        System.out.println();
        System.out.println("========= FitLife =========");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Listar clientes");
        System.out.println("3 - Listar planos");
        System.out.println("4 - Associar plano a um cliente");
        System.out.println("5 - Cadastrar treino");
        System.out.println("6 - Listar treinos de um cliente");
        System.out.println("7 - Processar pagamento");
        System.out.println("0 - Sair");
        System.out.println("===========================");
    }

    private void cadastrarCliente() {
        String nome = lerTexto("Nome: ");
        String email = lerTexto("E-mail: ");
        int idade = lerInteiro("Idade: ");

        Cliente cliente = clienteService.criar(nome, email, idade);
        System.out.println("Cliente cadastrado: " + cliente);
    }

    private void listarClientes() {
        List<Cliente> clientes = clienteService.listar();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        clientes.forEach(System.out::println);
    }

    private void listarPlanos() {
        for (Plano plano : planoService.listar()) {
            System.out.println("#" + plano.id + " - " + plano);
        }
    }

    private void associarPlano() {
        listarClientes();
        long clienteId = lerInteiro("Id do cliente: ");
        listarPlanos();
        long planoId = lerInteiro("Id do plano: ");

        Cliente cliente = clienteService.associarPlano(clienteId, planoId);
        System.out.println("Plano associado: " + cliente);
    }

    private void cadastrarTreino() {
        listarClientes();
        long clienteId = lerInteiro("Id do cliente: ");
        String nome = lerTexto("Nome do treino: ");
        String descricao = lerTexto("Descrição: ");

        Treino treino = treinoService.criar(nome, descricao, clienteId);
        System.out.println("Treino cadastrado: " + treino);
    }

    private void listarTreinosDoCliente() {
        long clienteId = lerInteiro("Id do cliente: ");
        List<Treino> treinos = treinoService.listarPorCliente(clienteId);
        if (treinos.isEmpty()) {
            System.out.println("Nenhum treino cadastrado para este cliente.");
            return;
        }

        treinos.forEach(System.out::println);
    }

    private void processarPagamento() {
        long clienteId = lerInteiro("Id do cliente: ");
        Cliente cliente = clienteService.buscarPorId(clienteId);
        pagamentoService.procPagamento(cliente);
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    // Repete a leitura até o usuário digitar um número inteiro válido
    private int lerInteiro(String mensagem) {
        while (true) {
            String entrada = lerTexto(mensagem).strip();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido!");
            }
        }
    }
}
