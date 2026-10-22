package org.acme.modules.plano.service;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import org.acme.modules.plano.models.Plano;
import org.acme.modules.plano.models.PlanoMensal;
import org.acme.modules.plano.models.PlanoPremium;

// Inicia planos no banco caso ele esteja vazio
@ApplicationScoped
public class PlanoSeed {
    @Transactional
    void aoIniciar(@Observes StartupEvent evento) {
        if (Plano.count() == 0) {
            new PlanoMensal().persist();
            new PlanoPremium().persist();
        }
    }
}
