package com.semicolon.africa.services;

import com.semicolon.africa.data.model.Otp;
import com.semicolon.africa.data.model.User;
import com.semicolon.africa.data.repository.OtpRepo;
import com.semicolon.africa.data.repository.UserRepos;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
    @Slf4j
    public class EmailSenderServices{
        private final JavaMailSender javaMailSender;

        OtpRepo otpRepo;
        @Autowired
        UserRepos userRepos;


        public EmailSenderServices(
                JavaMailSender javaMailSender, OtpRepo otpRepo
        ) {
            this.javaMailSender = javaMailSender;
            this.otpRepo=otpRepo;
        }

        @Async
        public void sendOTPVerificationMail(String userId, String email) {
            try {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");
                message.setTo(email);
                message.setSubject("Your One Time Password");
//            message.setText(buildEmail(), true);
                message.setText(String.valueOf(generateOTP(userId,email)), true);
                message.setFrom("digeratees@gmail.com");
                javaMailSender.send(mimeMessage);
            }
            catch (MessagingException | MailException e) {
                log.info("problem2: ");
                log.info(e.getMessage());
                throw new RuntimeException(e);
            }
        }

        public int generateOTP(String userId,String email){
            // generate 6 random digits
            // save it to the otp table in database
            SecureRandom secureRandom = new SecureRandom();
            Otp otp = new Otp();
               int generated =  secureRandom.nextInt() +7;
            otp.setOtpCode(generated);
            otp.setUserId(userId);
            otp.setEmail(email);
            otpRepo.save(otp);
            return generated;
        }
    public String confirmOTP(String userConfirmationOtp, String email) {
            for (Otp otp : otpRepo.findAll()) {
                if(userConfirmationOtp.equals(otp.getOtpCode())) {
                    User user = userRepos.findById(otp.getUserId()).get();
                    user.setEmail(email);
                }
                else {
                    throw new RuntimeException("OTP code does not match");
                }
            }
         return "email verified";
        }
}
