package com.edoe.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
public class Descriptor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonBackReference(value = "itens")
    @OneToMany(mappedBy = "descriptor")
    private List<Item> itens;

    @JsonBackReference(value = "required")
    @OneToMany(mappedBy = "descriptor")
    private List<ItemRequired> required;

    public Descriptor() {
    }

    public Descriptor(Long id, String name, List<Item> itens, List<ItemRequired> itemRequireds) {
        this.id = id;
        this.name = name;
        this.itens = itens;
        this.required = itemRequireds;
    }

    public List<ItemRequired> getRequired() {
        return required;
    }

    public void setRequired(List<ItemRequired> required) {
        this.required = required;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
