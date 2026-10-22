package org.acme.modules.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import org.acme.modules.models.Cliente;
import org.acme.modules.models.Plano;

@ApplicationScoped
public class ClienteService {

    @Inject
    EntityManager em;

    @Transactional
    public List<Cliente> listar() {
        return em.createQuery("from Cliente", Cliente.class).getResultList();
    }

    @Transactional
    public Cliente buscarPorId(Long id) {
        Cliente cliente = em.find(Cliente.class, id);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }
        return cliente;
    }

    @Transactional
    public Cliente criar(String nome, String email, Integer idade) {
        Cliente cliente = new Cliente(nome, email, idade);
        em.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente associarPlano(Long clienteId, Long planoId) {
        Cliente cliente = buscarPorId(clienteId);
        Plano plano = Plano.findById(planoId);
        if (plano == null) {
            throw new IllegalArgumentException("Plano não encontrado!");
        }

        cliente.setPlano(plano);
        return cliente;
    }
}
