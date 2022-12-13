package com.book.address.service;

import com.book.address.dto.ResponseDTO;
import com.book.address.model.Email;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService implements IEmailService {

    @Override
    public void sendMail(Email email) {
        final String FROM_EMAIL = "psthakur.1999@gmail.com";
        final String FROM_PWD = "ibvyzvoooluhbxka";

        // Setting properties for JMS (Java Mail Sender).
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");

        properties.put("mail.smtp.port", "587");

        properties.put("mail.smtp.auth", "true");

        properties.put("mail.smtp.starttls.enable", "true");

        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Authenticating email and password we set for our application.
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, FROM_PWD);
            }
        };

        // Creating a session.
        Session session = Session.getInstance(properties, authenticator);

        // Creating a MIME message.
        MimeMessage mail = new MimeMessage(session);

        try {
            mail.addHeader("Content-type", "text/HTML; charset=UTF-8");

            mail.addHeader("format", "flowed");

            mail.addHeader("Content-Transfer-Encoding", "8bit");

            mail.setFrom(new InternetAddress(FROM_EMAIL));

            // Setting recievers email.
            mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));

            // Setting subject of message.
            mail.setSubject(email.getSubject(), "UTF-8");

            // Setting message text(body).
            mail.setText(email.getBody(), "UTF-8");

            Transport.send(mail);

            ResponseDTO responseDTO = new ResponseDTO("Sent email!", mail, null);

//            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        ResponseDTO responseDTO = new ResponseDTO(" ERROR: Couldn't send email" , null, null);

//        return new ResponseEntity<ResponseDTO>
//                (responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
