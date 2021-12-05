package com.edoe.api.dto;

import com.edoe.api.entity.Item;

import java.io.Serializable;

public class ItemDonationDTO implements Serializable {
    private Long id;
    private String detalDonation;
    private Integer amountDonation;

    public ItemDonationDTO() {
    }

    public ItemDonationDTO(Long id, String detalDonation, Integer amountDonation) {
        this.id = id;
        this.detalDonation = detalDonation;
        this.amountDonation = amountDonation;
    }

    public ItemDonationDTO(Item item) {
        this.id = item.getId();
        this.detalDonation = detalDonation;
        this.amountDonation = amountDonation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetalDonation() {
        return detalDonation;
    }

    public void setDetalDonation(String detalDonation) {
        this.detalDonation = detalDonation;
    }

    public Integer getAmountDonation() {
        return amountDonation;
    }

    public void setAmountDonation(Integer amountDonation) {
        this.amountDonation = amountDonation;
    }
}
