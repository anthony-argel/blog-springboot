package com.anthonyargel.blog.DTOs;

public class CreatePostDTO {
    private String title;
    private Long categoryId;
    private String bannerImage;
    private String content;

    private String summary;

    public CreatePostDTO(String title, Long categoryId, String bannerImage, String content, String summary) {
        this.title = title;
        this.categoryId = categoryId;
        this.bannerImage = bannerImage;
        this.content = content;
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "CreatePostDTO{" +
                "title='" + title + '\'' +
                ", categoryId=" + categoryId +
                ", bannerImage='" + bannerImage + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
