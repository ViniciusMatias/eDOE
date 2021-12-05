package com.edoe.api.entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class ItemRequired implements Serializable {

    private static final long serialVersionUID = 5382534246709391566L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String motivation;
    private Integer amount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "descriptor_id")
    private Descriptor descriptor;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean deleted = false;

    public ItemRequired(Long id, String motivation, Integer amount, Descriptor descriptor, User user) {
        this.id = id;
        this.motivation = motivation;
        this.amount = amount;
        this.descriptor = descriptor;
        this.user = user;
    }

    public ItemRequired() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Descriptor getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(Descriptor descriptor) {
        this.descriptor = descriptor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
