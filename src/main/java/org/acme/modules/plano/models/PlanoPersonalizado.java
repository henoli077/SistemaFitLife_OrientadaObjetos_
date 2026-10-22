package org.acme.modules.plano.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PERSONALIZADO")
public class PlanoPersonalizado extends Plano {
    protected int meses;

    protected PlanoPersonalizado() { }

    public PlanoPersonalizado(String nome, double valorBase, int meses) {
        super(nome, valorBase);
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do plano é obrigatório!");
        }
        if (valorBase <= 0) {
            throw new IllegalArgumentException("Valor base deve ser maior que zero!");
        }
        if (meses < 1) {
            throw new IllegalArgumentException("Duração mínima é 1 mês!");
        }

        this.meses = meses;
    }

    @Override
    public double calcularMensalidade() {
        return valorBase / meses;
    }

    public int getMeses() {
        return meses;
    }
}