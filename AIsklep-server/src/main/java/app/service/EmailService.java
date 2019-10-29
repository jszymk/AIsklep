package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private MailSender mailSender;

    private static final String DOMAIN = "jszymk.ddns.net";

    @Async
    public void sendRegisterEmail(String email, String registerToken) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("AIsklep - link aktywacyjny");
        msg.setText("Link aktywacyjny: " + "http://" + DOMAIN + "/activate?token=" + registerToken);
        this.mailSender.send(msg);
    }

    @Async
    public void sendResetPasswordEmail(String email, String password) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("AIsklep - nowe hasło");
        msg.setText("Nowe hasło: " + password);
        this.mailSender.send(msg);
    }
}
