package com.example.eventplanner.service;

import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.User;

public interface EmailService {
    public void sendEventInvitation(User receiver, User sender, Event event, String mailFrom);
}
