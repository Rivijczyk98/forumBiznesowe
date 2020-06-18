package pl.edu.pb.wi.forumbiznesowe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.forumbiznesowe.service.interfaces.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    public JavaMailSender emailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public EmailServiceImpl() {

    }

    public void sendReplayNotificationEmail(
            String receiver, String subject, String text
    ){
        receiver = receiver.trim();

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(receiver);
        mail.setSubject(subject);
        mail.setText(text);

        emailSender.send(mail);
    }

    public void sendAutomaticGenerated(
            String receiver, Long postID
    ){
        receiver = receiver.trim();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(receiver);
        mail.setSubject("You got replay to your Post!");
        mail.setText("You've got an replay on your post: http:/localhost:4200/post?id=" + postID + " ! Check it, it may be important!\nBest regards from Forum Biznesowa Team");

        emailSender.send(mail);
    }

}
