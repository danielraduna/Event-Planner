package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.User;
import com.example.eventplanner.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    private final TemplateEngine templateEngine;

    @Override
    public void sendEventInvitation(User receiver, User sender, Event event, String mailFrom) {
        Context context = new Context();
        context.setVariable("event", event);
        context.setVariable("receiver", receiver);
        context.setVariable("sender",sender);

        String body = templateEngine.process("emailTemplate", context);

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        try {
            helper.setText(body, true);
            helper.setTo(receiver.getEmail());
            helper.setSubject("Invitație la eveniment");
            helper.setFrom(mailFrom);
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
