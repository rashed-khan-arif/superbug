package com.startup.superbug.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "medicine_image")
public class MedicineImage {
    @Column(name = "medicine_image_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SerializedName("medicineImageId")
    @Expose
    private long medicineImageId;

    @Column(name = "name")
    @SerializedName("name")
    @Expose
    private String name;

    @Column(name = "medicine_id")
    @SerializedName("medicineId")
    @Expose
    private int medicineId;

    @Column(name = "active")
    @SerializedName("active")
    @Expose
    private int active;

    @ManyToOne
    @JoinColumn(name="medicine_id",  insertable = false, updatable = false)
    private Medicine medicine;

    public long getMedicineImageId() {
        return medicineImageId;
    }

    public void setMedicineImageId(long medicineImageId) {
        this.medicineImageId = medicineImageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
