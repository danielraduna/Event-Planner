package com.example.eventplanner.service;

import com.example.eventplanner.model.ProfilePicture;
import com.example.eventplanner.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProfilePictureService {

    public List<ProfilePicture> getAllProfilePictures();

    public ProfilePicture getProfilePictureById(Long id);

    public ProfilePicture createProfilePicture(ProfilePicture profilePicture);

    public ProfilePicture updateProfilePicture(ProfilePicture profilePicture);

    public void deleteProfilePicture(Long id);

    public User setProfilePictureToUser(Long userId, Long profilePictureId);
}
