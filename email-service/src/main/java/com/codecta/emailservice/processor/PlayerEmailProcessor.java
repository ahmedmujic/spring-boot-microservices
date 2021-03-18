package com.codecta.emailservice.processor;

import com.codecta.emailservice.config.ConsumerConfiguration;
import com.codecta.emailservice.dto.EmailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component

public class PlayerEmailProcessor {

    public static final Logger LOGGER = LoggerFactory.getLogger(EmailResponse.class);

    @Autowired
    private JavaMailSender mailSender;

    @RabbitListener(queues = ConsumerConfiguration.ORDERS_QUEUE_NAME)
    public void processPlayer(EmailResponse emailResponse) {

        String from = "orbofq.noreply@gmail.com";
        String to = emailResponse.getEmail();

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(emailResponse.getContent());
        message.setText("Your score is:" + emailResponse.getScore());

        mailSender.send(message);


        System.out.println(emailResponse);
        //LOGGER.debug("player [{}] received !", player.getPlayerId());
    }
}
