package ics.Image.Classes;

public class ImageRequestDTO {
    private String imgUrl;
    private String token;

    // Getters and setters
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}