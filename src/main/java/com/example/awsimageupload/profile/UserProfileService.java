package com.example.awsimageupload.profile;

import com.example.awsimageupload.bucket.BucketName;
import com.example.awsimageupload.fileStorage.FileStorage;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStorage fileStorage;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStorage fileStorage) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStorage = fileStorage;
    }

    List<UserProfile> getUserProfiles(){
        return userProfileDataAccessService.getUserProfiles();
    }

    UserProfile getUserProfileById(UUID userProfileId){
        return userProfileDataAccessService.getUserProfileById(userProfileId);
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) throws IOException {
        // TODO : Check if image is not empty
        // TODO : Check if file is an image
        // TODO : Check if user exists in database
        // TODO : grab metadata from file if any
        // TODO : Store the image in s3 and update database with s3 image link

        if(file.isEmpty()){
            throw new IllegalStateException("cannot upload empty file [ " + file.getSize() + "]");
        }
        if(!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(), ContentType.IMAGE_PNG.getMimeType(), ContentType.IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("file must be an image");
        }

        UserProfile user = userProfileDataAccessService.getUserProfileById(userProfileId);

        if (user == null){
            throw new IllegalStateException("user profile with the id of " +  userProfileId + " doesn't exist");
        }

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type" , file.getContentType());
        metadata.put("Content-Length" , String.valueOf(file.getSize()));

        String path = String.format("%s/%s" , BucketName.PROFILE_IMAGE.getBucketName() , user.getUserProfileId());
        String filename = String.format("%s-%s" , file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStorage.save(path, filename , Optional.of(metadata) , file.getInputStream());
        }
        catch(IOException e) {
            throw new IllegalStateException(e);
        }




    }
}
