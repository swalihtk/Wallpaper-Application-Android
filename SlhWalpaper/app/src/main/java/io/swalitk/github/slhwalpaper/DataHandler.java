package io.swalitk.github.slhwalpaper;

public class DataHandler {
    String title, thumbnail, image;
    DataHandler(String title, String thumbnail, String image){
        this.title=title;
        this.thumbnail=thumbnail;
        this.image=image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}

