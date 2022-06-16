package com.example.awsimageupload.dataStorage;

import org.springframework.stereotype.Repository;
import com.example.awsimageupload.profile.UserProfile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),
                "janetjones",
                null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),
                "antoniojunior",
                null));
    }

    public List<UserProfile> getUserProfiles() {
        System.out.println(USER_PROFILES);
        return USER_PROFILES;
    }

    public UserProfile getUserProfileById(UUID userProfileId) {
        Iterator<UserProfile> iterator = USER_PROFILES.iterator();
        while(iterator.hasNext()){
            UserProfile userProfile = iterator.next();
            if(userProfile.getUserProfileId().equals(userProfileId)){
                return userProfile;
            }
        }
        return null;
    }
}
