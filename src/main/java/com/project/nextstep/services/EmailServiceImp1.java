package com.project.nextstep.services;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EmailServiceImp1 implements EmailService {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sender;
    private static final int SIZE= 6;

    @Autowired
    public EmailServiceImp1(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    private static String generateCode(){
        String code = "";
        for (int i = 0; i < SIZE; i++) {
           code =  code.concat(String.valueOf((int) (Math.random()*10)));
        }
        return code;
    }


    public Optional<String> sendMail(String mailTo){
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

            mimeMessageHelper.setSubject("Verify Your Email");

            String verificationCode = generateCode();

            String htmlMsg = "<html><body>"
                    + "<h1>Code Verification Email</h1>"
                    + "<p>Hello,</p>"
                    + "<p>Your verification code is:</p>"
                    + "<h2 style=\"color: #007bff; font-weight: bold; font-size: 24px;\">" + verificationCode + "</h2>"
                    + "<p>Please enter this code in the verification form to complete your registration process.</p>"
                    + "<p>If you did not request this code, please ignore this email.</p>"
                    + "<p>Thank you for using our services!</p>"
                    + "<h6>The NextStep team<h6>"
                    + "</body></html>";

            mimeMessageHelper.setText(htmlMsg,true);
            mimeMessageHelper.setTo(mailTo);
            mailSender.send(mimeMessage);
            return Optional.ofNullable(verificationCode);
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
