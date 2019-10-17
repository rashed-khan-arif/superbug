package com.startup.superbug.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article {
    @Column(name = "article_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SerializedName("articleId")
    @Expose
    private int articleId;

    @Column(name = "article_title")
    @SerializedName("articleTitle")
    @Expose
    private String articleTitle;

    @Column(name = "short_desc")
    @SerializedName("shortDesc")
    @Expose
    private String shortDesc;

    @Column(name = "long_desc")
    @SerializedName("longDesc")
    @Expose
    private String longDesc;

    @Column(name = "user_id")
    @SerializedName("userId")
    @Expose
    private int userId;

    @Column(name = "create_at")
    @SerializedName("createAt")
    @Expose
    private Date createAt;

    @Column(name = "active")
    @SerializedName("active")
    @Expose
    private int active;

    @Column(name = "published_at")
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;

    @Column(name = "thumb_image_title")
    @SerializedName("thumbImageTitle")
    @Expose
    private String thumbImageTitle;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getThumbImageTitle() {
        return thumbImageTitle;
    }

    public void setThumbImageTitle(String thumbImageTitle) {
        this.thumbImageTitle = thumbImageTitle;
    }
}
