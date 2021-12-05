package com.edoe.api.dto;

import com.edoe.api.entity.Descriptor;
import com.edoe.api.entity.Item;

import java.io.Serializable;

public class ItemDTO implements Serializable {

    private static final long serialVersionUID = 5644181454147853585L;

    private Long id;
    private String detail;
    private Integer amount;
    private Descriptor descriptor;
    private UserDTO user;


    public ItemDTO(Long id, String detail, Integer amount, Descriptor descriptor, UserDTO user) {
        this.id = id;
        this.detail = detail;
        this.amount = amount;
        this.descriptor = descriptor;
        this.user = user;
    }

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.detail = item.getDetail();
        this.amount = item.getAmount();
        this.descriptor = item.getDescriptor();
        this.user = new UserDTO(item.getUser());
    }

    public ItemDTO() {
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
}
