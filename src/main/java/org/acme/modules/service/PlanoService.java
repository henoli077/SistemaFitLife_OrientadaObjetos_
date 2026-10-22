package org.acme.modules.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import org.acme.modules.models.Plano;
import org.acme.modules.models.PlanoPersonalizado;

@ApplicationScoped
public class PlanoService {

    @Transactional
    public List<Plano> listar() {
        return Plano.listAll();
    }

    @Transactional
    public Plano criar(String nome, double valorBase, int meses) {
        PlanoPersonalizado plano = new PlanoPersonalizado(nome, valorBase, meses);
        plano.persist();
        return plano;
    }
}