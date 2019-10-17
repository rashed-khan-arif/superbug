package com.startup.superbug.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "medicine_group")
public class MedicineGroup {
    @Column(name = "group_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SerializedName("groupId")
    @Expose
    private Integer groupId;

    @Column(name = "group_name")
    @SerializedName("groupName")
    @Expose
    private String groupName;

    @SerializedName("medicines")
    @JsonBackReference
    @Expose
    @OneToMany(mappedBy = "medicineGroup", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Medicine> medicines;


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
