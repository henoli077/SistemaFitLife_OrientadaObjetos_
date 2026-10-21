package org.acme.modules;

import org.acme.modules.plano.models.Plano;
import org.acme.modules.plano.models.PlanoMensal;
import org.acme.modules.plano.models.PlanoPersonalizado;
import org.acme.modules.plano.models.PlanoPremium;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlanoTeste {

    @Test
    public void calcularMensalidadeDoPlanoMensal() {
        Plano plano = new PlanoMensal();
        assertEquals(100.0, plano.calcularMensalidade());
    }

    @Test
    public void calcularPremiumDoPlanoPremium() {
        Plano plano = new PlanoPremium();
        assertEquals(200.0, plano.calcularMensalidade());
    }

    @Test
    public void retornarNomeDoPlano() {
        Plano planoMensal = new PlanoMensal();
        Plano planoPremium = new PlanoPremium();

        assertEquals("Mensal", planoMensal.getNome());
        assertEquals("Premium", planoPremium.getNome());
    }

    @Test
    public void criarPlanoPersonalizado() {
        Plano plano = new PlanoPersonalizado("Trimestral", 90.0, 3);

        assertEquals("Trimestral", plano.getNome());
        assertEquals(90.0, plano.getValor());
        assertEquals(3, plano.getMeses());
    }

    @Test
    public void rejeitarNomeNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PlanoPersonalizado(null, 90.0, 3)
        );
    }

    @Test
    public void rejeitarNomeVazio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PlanoPersonalizado("   ", 90.0, 3)
        );
    }

    @Test
    public void rejeitarValorNegativo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PlanoPersonalizado("Trimestral", -90.0, 3)
        );
    }

    @Test
    public void rejeitarValorNaN() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PlanoPersonalizado(
                        "Trimestral", Double.NaN, 3
                )
        );
    }

    @Test
    public void rejeitarValorInfinito() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PlanoPersonalizado(
                        "Trimestral", Double.POSITIVE_INFINITY, 3
                )
        );
    }
}
