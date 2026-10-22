package org.acme.modules.plano.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MENSAL")
public class PlanoMensal extends Plano {

    public PlanoMensal() {
        super("Mensal", 100.0);
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
