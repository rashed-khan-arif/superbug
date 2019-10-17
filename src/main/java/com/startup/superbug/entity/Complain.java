package com.startup.superbug.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "complain")
public class Complain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complain_id")
    @Expose
    @SerializedName("complainId")
    private Integer complainId;

    @Expose
    @SerializedName("providerName")
    @Column(name = "provider_name")
    private String providerName;

    @Expose
    @SerializedName("farmacyName")
    @Column(name = "farmacy_name")
    private String farmacyName;

    @Expose
    @SerializedName("address")
    @Column(name = "address")
    private String address;

    @Expose
    @SerializedName("details")
    @Column(name = "details")
    private String details;

    @Expose
    @SerializedName("prescribed")
    @Column(name = "is_prescribed")
    private Boolean isPrescribed;

    @Expose
    @SerializedName("prescriptionImage")
    @Column(name = "prescription_image")
    private String prescriptionImage;

    @Expose
    @SerializedName("userId")
    @Column(name = "user_id")
    private int userId;

    @Expose
    @SerializedName("active")
    @Column(name = "active")
    private Boolean active;

    @Expose
    @SerializedName("createAt")
    @Column(name = "create_at")
    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;


    public Integer getComplainId() {
        return complainId;
    }

    public void setComplainId(Integer complainId) {
        this.complainId = complainId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getFarmacyName() {
        return farmacyName;
    }

    public void setFarmacyName(String farmacyName) {
        this.farmacyName = farmacyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getPrescribed() {
        return isPrescribed;
    }

    public void setPrescribed(Boolean prescribed) {
        isPrescribed = prescribed;
    }

    public String getPrescriptionImage() {
        return prescriptionImage;
    }

    public void setPrescriptionImage(String prescriptionImage) {
        this.prescriptionImage = prescriptionImage;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
