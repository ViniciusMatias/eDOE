package com.edoe.api.dto;

import com.edoe.api.entity.Descriptor;
import com.edoe.api.entity.ItemRequired;

import java.io.Serializable;


public class ItemRequiredDTO implements Serializable {

    private static final long serialVersionUID = 3429552897709302215L;

    private Long id;
    private String motivation;
    private Integer amount;
    private Descriptor descriptor;
    private UserDTO user;

    public ItemRequiredDTO() {
    }

    public ItemRequiredDTO(Long id, String motivation, Integer amount, Descriptor descriptor, UserDTO user) {
        this.id = id;
        this.motivation = motivation;
        this.amount = amount;
        this.descriptor = descriptor;
        this.user = user;
    }

    public ItemRequiredDTO(ItemRequired itemRequired) {
        this.id = itemRequired.getId();
        this.motivation = itemRequired.getMotivation();
        this.amount = itemRequired.getAmount();
        this.descriptor = itemRequired.getDescriptor();
        this.user = new UserDTO(itemRequired.getUser());
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
