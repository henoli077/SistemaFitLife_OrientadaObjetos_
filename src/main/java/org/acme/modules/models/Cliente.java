package org.acme.modules.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private Integer idade;

    @ManyToOne
    @JoinColumn(name = "plano_id", nullable = true)
    private Plano plano;

    //Construtor do Hibernate
    public Cliente(){

    }

    //Construtor da Classe
    public Cliente(String nome, String email, Integer idade){
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do cliente é obrigatório!"
            );

        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "O email do cliente é obrigatório!"
            );
        }

        if (idade == null || idade < 15) {
            throw new IllegalArgumentException(
                    "A idade mínima é 15 anos!"
            );
        }

        this.nome = nome.strip();
        this.email = email.strip();
        this.idade = idade;
        this.plano = null;
    }

    //Getters e Setters dos atributos da Classe
    public Long getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do cliente é obrigatório!"
            );

        }

        this.nome = nome.strip();
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "O email do cliente é obrigatório!"
            );
        }

        this.email = email.strip();
    }

    public Integer getIdade() {
        return this.idade;
    }

    public void setIdade(Integer idade) {
        if (idade == null || idade < 15) {
            throw new IllegalArgumentException(
                    "A idade mínima é 15 anos!"
            );
        }

        this.idade = idade;
    }

    public Plano getPlano() {
        return this.plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    @Override
    public String toString() {
        String nomePlano = (plano == null) ? "Sem plano" : plano.getNome();
        return String.format("#%d - %s | %s | %d anos | Plano: %s",
                id, nome, email, idade, nomePlano);
    }
}
