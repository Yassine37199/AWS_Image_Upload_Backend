package com.example.awsimageupload.bucket;

public enum BucketName {
    PROFILE_IMAGE("spring-image-upload-7319");

    private final String bucketName;


    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
