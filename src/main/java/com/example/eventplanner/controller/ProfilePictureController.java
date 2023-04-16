package com.example.eventplanner.controller;

import com.example.eventplanner.model.ProfilePicture;
import com.example.eventplanner.model.User;
import com.example.eventplanner.service.ProfilePictureService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile-pictures")
@CrossOrigin(origins = "http://localhost:4200/")
@AllArgsConstructor
public class ProfilePictureController {
    private final ProfilePictureService profilePictureService;

    @GetMapping
    public ResponseEntity<List<ProfilePicture>> getAllProfilePictures() {
        List<ProfilePicture> profilePictures = profilePictureService.getAllProfilePictures();
        return new ResponseEntity<>(profilePictures, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfilePicture> getProfilePictureById(@PathVariable Long id) {
        ProfilePicture profilePicture = profilePictureService.getProfilePictureById(id);
        return new ResponseEntity<>(profilePicture, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<ProfilePicture> createProfilePicture(@RequestParam("userId") Long userId, @RequestParam("imageData") String imageData) {
        ProfilePicture profilePicture = new ProfilePicture();
        profilePicture.setImageData(imageData);
        ProfilePicture newProfilePicture = profilePictureService.createProfilePicture(profilePicture, userId);
        return new ResponseEntity<>(newProfilePicture, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProfilePicture> updateProfilePicture(@PathVariable Long id, @RequestBody ProfilePicture profilePicture) {
        profilePicture.setId(id);
        ProfilePicture updatedProfilePicture = profilePictureService.updateProfilePicture(profilePicture);
        return new ResponseEntity<>(updatedProfilePicture, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfilePicture(@PathVariable Long id) {
        profilePictureService.deleteProfilePicture(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/assignProfilePictureToUser")
    public ResponseEntity<User> setProfilePictureToUser(@RequestParam("userId") Long userId, @RequestParam("profilePictureId") Long profilePictureId) {
        User user = profilePictureService.setProfilePictureToUser(userId, profilePictureId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

}