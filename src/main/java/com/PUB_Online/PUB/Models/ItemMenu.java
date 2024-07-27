package com.PUB_Online.PUB.Models;

import javax.persistence.*;

@Entity
@Table(name="itens_menu")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ItemMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", un)
    private Long id;
    
    private String nome;
    private Double preco;

    public ItemMenu() {}

    public ItemMenu(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
