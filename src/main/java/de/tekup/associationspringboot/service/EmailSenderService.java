package de.tekup.associationspringboot.service;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmailSenderService {
    private JavaMailSender javaMailSender;
    public void sendmail(String destinataire) throws  jakarta.mail.MessagingException {
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("mokhtarabbes9@gmail.com");
        mimeMessageHelper.setTo("mokhtarabbes9@gmail.com");
        mimeMessageHelper.setText("body of mail");
        mimeMessageHelper.setSubject("Demande accepté");
        javaMailSender.send(mimeMessage);
        System.out.println("email envoyée");

    }
}
