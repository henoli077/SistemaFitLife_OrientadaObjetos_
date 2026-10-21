package org.acme.modules.plano.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import org.acme.modules.plano.models.Plano;
import org.acme.modules.plano.models.PlanoPersonalizado;

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