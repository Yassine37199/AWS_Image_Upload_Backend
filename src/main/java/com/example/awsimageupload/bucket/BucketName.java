package com.example.awsimageupload.bucket;

public enum BucketName {
    PROFILE_IMAGE("spring-image-upload-3719");

    private final String bucketName;


    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
