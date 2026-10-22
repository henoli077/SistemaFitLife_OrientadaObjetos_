package org.acme.modules.plano.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Plano  extends PanacheEntity {
    @Column(nullable = false)
    protected String nome;

    @Column(nullable = false)
    protected double valorBase;

    // Construtor exigido pelo Hibernate
    protected Plano() { }

    public Plano(String nome, double valorBase) {
        setNome(nome);
        setValorBase(valorBase);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do plano é obrigatório!");
        }

        this.nome = nome.strip();
    }

    public double getValor () { return valorBase;}

    public void setValorBase(double valorBase) {
        if (!Double.isFinite(valorBase) || valorBase < 0) {
            throw new IllegalArgumentException("O valor base deve ser válido e não negativo!");
        }

        this.valorBase = valorBase;
    }

    public abstract double calcularMensalidade();

    @Override
    public String toString() {
        return String.format("Plano %s - R$ %.2f", nome, calcularMensalidade());
    }

    public abstract int getMeses();
}
