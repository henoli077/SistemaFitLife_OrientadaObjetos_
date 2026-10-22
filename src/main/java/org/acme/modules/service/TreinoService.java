package org.acme.modules.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import org.acme.modules.models.Cliente;
import org.acme.modules.models.Treino;

@ApplicationScoped
public class TreinoService {

    @Inject
    EntityManager em;

    @Transactional
    public List<Treino> listar() {
        return em.createQuery("from Treino", Treino.class).getResultList();
    }

    @Transactional
    public List<Treino> listarPorCliente(Long clienteId) {
        if (em.find(Cliente.class, clienteId) == null) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }

        return em.createQuery("from Treino where cliente.id = :clienteId", Treino.class)
                .setParameter("clienteId", clienteId)
                .getResultList();
    }

    @Transactional
    public Treino criar(String nome, String descricao, Long clienteId) {
        Cliente cliente = em.find(Cliente.class, clienteId);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }

        Treino treino = new Treino(nome, descricao, cliente);
        em.persist(treino);
        return treino;
    }
}
