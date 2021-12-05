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
   @JsonBackReference
    @OneToMany(mappedBy = "descriptor" , cascade = CascadeType.ALL)
    private List<Item> itens;




    public Descriptor() {
    }

    public Descriptor(Long id, String name, List<Item> itens) {
        this.id = id;
        this.name = name;
        this.itens = itens;
       // this.Required = itensRequired;
    }

//    public List<ItemRequired> getRequired() {
//        return Required;
//    }
//
//    public void setRequired(List<ItemRequired> required) {
//        this.Required = required;
//    }

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
