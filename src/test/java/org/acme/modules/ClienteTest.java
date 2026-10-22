package org.acme.modules;

import org.acme.modules.models.Cliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    // 1. Testa a criação de um cliente com dados válidos
    @Test
    void deveCadastrarClienteComDadosValidos() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        assertEquals("João Silva", cliente.getNome());
        assertEquals("joao@email.com", cliente.getEmail());
        assertEquals(22, cliente.getIdade());
        assertNull(cliente.getPlano());
    }

    // 2. Testa a validação de nome nulo
    @Test
    void naoDeveCadastrarClienteComNomeNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Cliente(null, "joao@email.com", 22)
        );
    }

    // 3. Testa a validação de nome vazio
    @Test
    void naoDeveCadastrarClienteComNomeVazio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Cliente("", "joao@email.com", 22)
        );
    }

    // 4. Testa a validação de nome composto apenas por espaços
    @Test
    void naoDeveCadastrarClienteComNomeEmBranco() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Cliente("   ", "joao@email.com", 22)
        );
    }

    // 5. Testa a validação de e-mail nulo
    @Test
    void naoDeveCadastrarClienteComEmailNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Cliente("João Silva", null, 22)
        );
    }

    // 6. Testa a validação de e-mail vazio
    @Test
    void naoDeveCadastrarClienteComEmailVazio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Cliente("João Silva", "", 22)
        );
    }

    // 7. Testa a validação de e-mail composto apenas por espaços
    @Test
    void naoDeveCadastrarClienteComEmailEmBranco() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Cliente("João Silva", "   ", 22)
        );
    }

    // 8. Testa a validação de idade nula
    @Test
    void naoDeveCadastrarClienteComIdadeNula() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Cliente("João Silva", "joao@email.com", null)
        );
    }

    // 9. Testa a validação de idade inferior à mínima
    @Test
    void naoDeveCadastrarClienteComIdadeInferiorAMinima() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Cliente("João Silva", "joao@email.com", 14)
        );
    }

    // 10. Testa a atualização do nome
    @Test
    void deveAtualizarNomeDoCliente() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        cliente.setNome("Pedro Silva");

        assertEquals("Pedro Silva", cliente.getNome());
    }

    // 11. Testa a atualização do e-mail
    @Test
    void deveAtualizarEmailDoCliente() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        cliente.setEmail("pedro@email.com");

        assertEquals("pedro@email.com", cliente.getEmail());
    }

    // 12. Testa a atualização da idade
    @Test
    void deveAtualizarIdadeDoCliente() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        cliente.setIdade(30);

        assertEquals(30, cliente.getIdade());
    }

    // 13. Testa a rejeição de um nome inválido no setter
    @Test
    void naoDeveAtualizarNomeComValorInvalido() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> cliente.setNome(" ")
        );
    }

    // 14. Testa a rejeição de um e-mail inválido no setter
    @Test
    void naoDeveAtualizarEmailComValorInvalido() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> cliente.setEmail(null)
        );
    }

    // 15. Testa a rejeição de uma idade inválida no setter
    @Test
    void naoDeveAtualizarIdadeComValorInvalido() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                22
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> cliente.setIdade(10)
        );
    }
}
