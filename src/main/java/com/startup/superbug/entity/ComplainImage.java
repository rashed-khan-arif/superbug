package com.startup.superbug.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ComplainImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_image_id")
    @Expose
    @SerializedName("complainImageId")
    private Integer complainImageId;

    @Column(name = "image_title")
    @Expose
    @SerializedName("imageTitle")
    private String imageTitle;

    @Column(name = "complain_id")
    @Expose
    @SerializedName("complainId")
    private String complainId;

    @Column(name = "active")
    @Expose
    @SerializedName("active")
    private Boolean active;

    @Column(name = "createAt")
    @Expose
    @SerializedName("create_at")
    private Boolean createAt;

    public Integer getComplainImageId() {
        return complainImageId;
    }

    public void setComplainImageId(Integer complainImageId) {
        this.complainImageId = complainImageId;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Boolean createAt) {
        this.createAt = createAt;
    }

    public String getComplainId() {
        return complainId;
    }

    public void setComplainId(String complainId) {
        this.complainId = complainId;
    }
}
