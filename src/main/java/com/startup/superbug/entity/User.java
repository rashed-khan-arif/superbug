package com.startup.superbug.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity(name = "user")
public class User {
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SerializedName("userId")
    @Expose
    private long userId;

    @Column(name = "email")
    @SerializedName("email")
    @Expose
    private String email;

    @Column(name = "full_name")
    @SerializedName("fullName")
    @Expose
    private String fullName;

    @Column(name = "password")
    @SerializedName("password")
    @Expose(serialize = false)
    private String password;

    @Column(name = "active")
    @SerializedName("active")
    @Expose
    private int active;

//    @Column(name = "create_at")
//    @SerializedName("createAt")
//    @Expose
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
//    private Date create_at;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Expose(deserialize = false)
    private Set<Role> roles;

    @SerializedName("complains")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Complain> complains;

    public int getUserId() {
        return (int) userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
//
//    public Date getCreate_at() {
//        return create_at;
//    }
//
//    public void setCreate_at(Date create_at) {
//        this.create_at = create_at;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
