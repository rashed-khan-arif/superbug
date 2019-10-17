package com.startup.superbug.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "country")
public class Country {
    @Expose
    @Column(name = "country_id")
    @SerializedName("countryId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long countryId;
    @Expose
    @SerializedName("name")
    @Column(name = "name")
    private String name;

    @Expose
    @SerializedName("code")
    @Column(name = "code")
    private String code;

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
