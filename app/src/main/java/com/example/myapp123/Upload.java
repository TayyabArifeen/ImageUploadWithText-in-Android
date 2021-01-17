package com.example.myapp123;

public class Upload {
    private String mName;
    private String mImageUrl;
    public Upload() {
        //empty constructor needed
    }
    public Upload(String name, String imageUrl) {
       
        this.mName = name;
        this.mImageUrl = imageUrl;
    }
    public String getName() {
        return mName;
    }
    public void setName(String name) {
        mName = name;
    }
    public String getImageUrl() {
        return mImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
