package org.acme.modules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreinoTest {

    // 1. Testa a criação de um treino com dados válidos
    @Test
    void deveCriarTreinoComDadosValidos() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        Treino treino = new Treino(
                "Treino A",
                "Treino de membros superiores",
                cliente
        );

        assertEquals("Treino A", treino.getNome());
        assertEquals(
                "Treino de membros superiores",
                treino.getDescricao()
        );
        assertSame(cliente, treino.getCliente());
        assertNull(treino.getId());
    }

    // 2. Testa a remoção de espaços nas extremidades
    @Test
    void deveRemoverEspacosDasExtremidadesDosTextos() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        Treino treino = new Treino(
                "  Treino A  ",
                "  Treino de pernas  ",
                cliente
        );

        assertEquals("Treino A", treino.getNome());
        assertEquals("Treino de pernas", treino.getDescricao());
    }

    // 3. Testa nome nulo
    @Test
    void naoDeveCriarTreinoComNomeNulo() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Treino(null, "Treino de pernas", cliente)
        );
    }

    // 4. Testa nome vazio
    @Test
    void naoDeveCriarTreinoComNomeVazio() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Treino("", "Treino de pernas", cliente)
        );
    }

    // 5. Testa nome composto apenas por espaços
    @Test
    void naoDeveCriarTreinoComNomeEmBranco() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Treino("   ", "Treino de pernas", cliente)
        );
    }

    // 6. Testa descrição nula
    @Test
    void naoDeveCriarTreinoComDescricaoNula() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Treino("Treino A", null, cliente)
        );
    }

    // 7. Testa descrição vazia
    @Test
    void naoDeveCriarTreinoComDescricaoVazia() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Treino("Treino A", "", cliente)
        );
    }

    // 8. Testa descrição composta apenas por espaços
    @Test
    void naoDeveCriarTreinoComDescricaoEmBranco() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Treino("Treino A", "   ", cliente)
        );
    }

    // 9. Testa a associação obrigatória com o cliente
    @Test
    void naoDeveCriarTreinoSemCliente() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Treino(
                        "Treino A",
                        "Treino de pernas",
                        null
                )
        );
    }

    // 10. Testa a atualização do nome
    @Test
    void deveAtualizarNomeDoTreino() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        Treino treino = new Treino(
                "Treino A",
                "Treino de pernas",
                cliente
        );

        treino.setNome("Treino B");

        assertEquals("Treino B", treino.getNome());
    }

    // 11. Testa a atualização da descrição
    @Test
    void deveAtualizarDescricaoDoTreino() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        Treino treino = new Treino(
                "Treino A",
                "Treino de pernas",
                cliente
        );

        treino.setDescricao("Treino de membros superiores");

        assertEquals(
                "Treino de membros superiores",
                treino.getDescricao()
        );
    }

    // 12. Testa a atualização do cliente associado
    @Test
    void deveAtualizarClienteDoTreino() {
        Cliente cliente1 = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        Cliente cliente2 = new Cliente(
                "Maria Silva",
                "maria@email.com",
                25
        );

        Treino treino = new Treino(
                "Treino A",
                "Treino de pernas",
                cliente1
        );

        treino.setCliente(cliente2);

        assertSame(cliente2, treino.getCliente());
    }

    // 13. Testa nome inválido no setter
    @Test
    void naoDeveAtualizarNomeComValorInvalido() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        Treino treino = new Treino(
                "Treino A",
                "Treino de pernas",
                cliente
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> treino.setNome(" ")
        );
    }

    // 14. Testa descrição inválida no setter
    @Test
    void naoDeveAtualizarDescricaoComValorInvalido() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        Treino treino = new Treino(
                "Treino A",
                "Treino de pernas",
                cliente
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> treino.setDescricao(null)
        );
    }

    // 15. Testa a rejeição de cliente nulo no setter
    @Test
    void naoDeveAtualizarClienteParaNulo() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        Treino treino = new Treino(
                "Treino A",
                "Treino de pernas",
                cliente
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> treino.setCliente(null)
        );
    }

    // 16. Testa a remoção de espaços ao atualizar o nome
    @Test
    void deveRemoverEspacosAoAtualizarNome() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        Treino treino = new Treino(
                "Treino A",
                "Treino de pernas",
                cliente
        );

        treino.setNome("  Treino B  ");

        assertEquals("Treino B", treino.getNome());
    }

    // 17. Testa a remoção de espaços ao atualizar a descrição
    @Test
    void deveRemoverEspacosAoAtualizarDescricao() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        Treino treino = new Treino(
                "Treino A",
                "Treino de pernas",
                cliente
        );

        treino.setDescricao("  Treino de costas  ");

        assertEquals(
                "Treino de costas",
                treino.getDescricao()
        );
    }
}
