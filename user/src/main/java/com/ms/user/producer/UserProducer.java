package com.ms.user.producer;

import com.ms.user.dtos.EmailRecordDto;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(UserModel userModel) {

        String subject = "Cadastro realizado com sucesso";
        String text = userModel.getName() + ", Seja Bem Vindo(a)!\n"+ "Agradecemos o seu cadastro, aproveite agora"
                + " todos os recursos da nossa plataforma!";

        var emailRecordDto = new EmailRecordDto(
                userModel.getUserId(),
                userModel.getEmail(),
                subject,
                text
        );

        rabbitTemplate.convertAndSend("", routingKey, emailRecordDto);
    }
}
