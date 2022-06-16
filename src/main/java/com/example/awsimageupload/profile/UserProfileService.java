package com.example.awsimageupload.profile;

import com.example.awsimageupload.fileStorage.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

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

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        // TODO : Check if image is not empty
        // TODO : Check if file is an image
        // TODO : Check if user exists in database
        // TODO : grab metadata from file if any
        // TODO : Store the image in s3 and update database with s3 image link
        if(!file.isEmpty() && file.getContentType() == "image/jpeg") {
            if(userProfileDataAccessService.getUserProfileById(userProfileId) != null){
                fileStorage.save();
            }
        }
    }
}
