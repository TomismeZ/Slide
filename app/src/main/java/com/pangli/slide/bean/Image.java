package com.pangli.slide.bean;

/**
 * Created by Administrator on 2018/1/18 0018.
 */

public class Image {
    private String id;
    private String title;
    private String imgUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
