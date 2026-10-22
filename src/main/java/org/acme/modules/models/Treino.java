package org.acme.modules.models;

import jakarta.persistence.*;

@Entity
public class Treino {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    //Construtor do Hibernate
    public Treino(){

    }

    //Construtor da Classe
    public Treino(String nome, String descricao, Cliente cliente) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do treino é obrigatório!"
            );

        }

        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException(
                    "A descrição do treino é obrigatória!"
            );

        }

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "É obrigatório a associação de um cliente ao treino!"
            );
        }

        this.nome = nome.strip();
        this.descricao = descricao.strip();
        this.cliente = cliente;
    }

    //Getters e Setters do atributos
    public Long getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do treino é obrigatório!"
            );

        }

        this.nome = nome.strip();
    }

    public String getDescricao(){
        return this.descricao;
    }

    public void setDescricao(String descricao){
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException(
                    "A descrição do treino é obrigatória!"
            );

        }

        this.descricao = descricao.strip();
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public void setCliente(Cliente cliente){
        if (cliente == null) {
            throw new IllegalArgumentException(
                    "É obrigatório a associação de um cliente ao treino!"
            );
        }

        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return String.format("#%d - %s: %s | Cliente: %s",
                id, nome, descricao, cliente.getNome());
    }
}
