package com.edoe.api.entity;

import com.edoe.api.dto.ItemDonationDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Donation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String userReceptor;
    private String userDonation;
    private String detailItem;
    private Integer amountDonation;


    public Donation(Long id, LocalDate date, String userReceptor, String userDonation, String detailItem, Integer amountDonation) {
        this.id = id;
        this.date = date;
        this.userReceptor = userReceptor;
        this.userDonation = userDonation;
        this.detailItem = detailItem;
        this.amountDonation =  amountDonation;

    }

    public Donation() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUserReceptor() {
        return userReceptor;
    }

    public void setUserReceptor(String userReceptor) {
        this.userReceptor = userReceptor;
    }

    public String getUserDonation() {
        return userDonation;
    }

    public void setUserDonation(String userDonation) {
        this.userDonation = userDonation;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDetailItem() {
        return detailItem;
    }

    public void setDetailItem(String detailItem) {
        this.detailItem = detailItem;
    }


    public Integer getAmountDonation() {
        return amountDonation;
    }

    public void setAmountDonation(Integer amountDonation) {
        this.amountDonation = amountDonation;
    }
}
