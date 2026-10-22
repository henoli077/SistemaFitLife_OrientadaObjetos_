package org.acme.modules.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PREMIUM")
public class PlanoPremium extends Plano {

    public PlanoPremium() {
        super("Premium", 200.0);
    }

    @Override
    public double calcularMensalidade() {
        return valorBase;
    }

    @Override
    public int getMeses() {
        return 1;
    }
}
