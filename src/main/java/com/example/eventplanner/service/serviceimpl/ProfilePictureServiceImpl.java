package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.exception.UserNotFoundException;
import com.example.eventplanner.model.ProfilePicture;
import com.example.eventplanner.model.User;
import com.example.eventplanner.repository.ProfilePictureRepository;
import com.example.eventplanner.repository.UserRepository;
import com.example.eventplanner.service.ProfilePictureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfilePictureServiceImpl implements ProfilePictureService {
        private final ProfilePictureRepository profilePictureRepository;
        private final UserRepository userRepository;

        public List<ProfilePicture> getAllProfilePictures() {
            return profilePictureRepository.findAll();
        }

        public ProfilePicture getProfilePictureById(Long id) {
            return profilePictureRepository.findById(id).orElse(null);
        }

    public ProfilePicture createProfilePicture(ProfilePicture profilePicture, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        profilePicture.setUser(user);
        user.setProfilePicture(profilePicture);
        profilePictureRepository.save(profilePicture);
        userRepository.save(user);
        return profilePicture;
    }


    public ProfilePicture updateProfilePicture(ProfilePicture profilePicture) {
            return profilePictureRepository.save(profilePicture);
        }

        public void deleteProfilePicture(Long id) {
            profilePictureRepository.deleteById(id);
        }

    @Override
    public User setProfilePictureToUser(Long userId, Long profilePictureId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<ProfilePicture> profilePictureOptional = profilePictureRepository.findById(profilePictureId);

        if (userOptional.isPresent() && profilePictureOptional.isPresent()) {
            User user = userOptional.get();
            ProfilePicture profilePicture = profilePictureOptional.get();

            user.setProfilePicture(profilePicture);
            profilePicture.setUser(user);

            userRepository.save(user);
            profilePictureRepository.save(profilePicture);

            return user;
        } else {
            return null;
        }
    }
}
