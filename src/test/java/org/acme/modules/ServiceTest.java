package org.acme.modules;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.List;
import org.acme.modules.models.Cliente;
import org.acme.modules.models.Plano;
import org.acme.modules.models.Treino;
import org.acme.modules.service.ClienteService;
import org.acme.modules.service.PagamentoService;
import org.acme.modules.service.PlanoService;
import org.acme.modules.service.TreinoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ServiceTest {

    @Inject
    ClienteService clienteService;

    @Inject
    PlanoService planoService;

    @Inject
    TreinoService treinoService;

    @Inject
    PagamentoService pagamentoService;

    // 1. O seed deve cadastrar os planos Mensal e Premium
    @Test
    void deveExistirPlanosMensalEPremium() {
        List<String> nomes = planoService.listar().stream()
                .map(Plano::getNome)
                .toList();

        assertTrue(nomes.contains("Mensal"));
        assertTrue(nomes.contains("Premium"));
    }

    // 2. Testa o cadastro e a busca de um cliente
    @Test
    void deveCadastrarEBuscarCliente() {
        Cliente criado = clienteService.criar("Ana Souza", "ana@email.com", 25);

        Cliente encontrado = clienteService.buscarPorId(criado.getId());

        assertEquals("Ana Souza", encontrado.getNome());
        assertNull(encontrado.getPlano());
    }

    // 3. Testa a busca de um cliente inexistente
    @Test
    void naoDeveEncontrarClienteInexistente() {
        assertThrows(
                IllegalArgumentException.class,
                () -> clienteService.buscarPorId(-1L)
        );
    }

    // 4. Testa a associação de um plano a um cliente
    @Test
    void deveAssociarPlanoAoCliente() {
        Cliente cliente = clienteService.criar("Bruno Lima", "bruno@email.com", 30);
        Plano plano = planoService.listar().get(0);

        clienteService.associarPlano(cliente.getId(), plano.id);

        Cliente atualizado = clienteService.buscarPorId(cliente.getId());
        assertEquals(plano.getNome(), atualizado.getPlano().getNome());
    }

    // 5. Testa a associação de um plano inexistente
    @Test
    void naoDeveAssociarPlanoInexistente() {
        Cliente cliente = clienteService.criar("Carla Dias", "carla@email.com", 40);

        assertThrows(
                IllegalArgumentException.class,
                () -> clienteService.associarPlano(cliente.getId(), -1L)
        );
    }

    // 6. Testa o cadastro de treinos e a listagem por cliente
    @Test
    void deveCadastrarEListarTreinosDoCliente() {
        Cliente cliente = clienteService.criar("Diego Rocha", "diego@email.com", 19);
        Cliente outro = clienteService.criar("Eva Martins", "eva@email.com", 21);

        treinoService.criar("Peito", "Supino e crucifixo", cliente.getId());
        treinoService.criar("Pernas", "Agachamento e leg press", cliente.getId());
        treinoService.criar("Costas", "Remada e puxada", outro.getId());

        List<Treino> treinos = treinoService.listarPorCliente(cliente.getId());

        assertEquals(2, treinos.size());
    }

    // 7. Testa o cadastro de treino para cliente inexistente
    @Test
    void naoDeveCadastrarTreinoParaClienteInexistente() {
        assertThrows(
                IllegalArgumentException.class,
                () -> treinoService.criar("Peito", "Supino", -1L)
        );
    }

    // 8. Testa o pagamento de um cliente com plano
    @Test
    void deveProcessarPagamentoDeClienteComPlano() {
        Cliente cliente = clienteService.criar("Fábio Melo", "fabio@email.com", 33);
        Plano plano = planoService.listar().get(0);
        clienteService.associarPlano(cliente.getId(), plano.id);

        Cliente comPlano = clienteService.buscarPorId(cliente.getId());

        assertDoesNotThrow(() -> pagamentoService.procPagamento(comPlano));
    }

    // 9. Testa o pagamento de um cliente sem plano
    @Test
    void naoDeveProcessarPagamentoDeClienteSemPlano() {
        Cliente cliente = clienteService.criar("Gabi Nunes", "gabi@email.com", 28);

        assertThrows(
                IllegalStateException.class,
                () -> pagamentoService.procPagamento(cliente)
        );
    }
}
