package com.ms.user.producers;

import com.ms.user.dtos.EmailDto;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// Producer é a classe responsável por enviar ou publicar mensagens para uma fila ou tópico
// RabbitTemplate é um bean fornecido pelo Spring AMQP utilizado para enviar mensagens para o RabbitMQ
// A anotação @Value é usada para injetar valores a partir do arquivo de propriedades

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(UserModel userModel) {

        // O objeto emailDTO é criado
        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!");
        emailDto.setText(userModel.getName() + ", seja bem vindo(a)! \nAgradecemos o seu cadastro, aproveite agora todos os recursos da nossa plataforma!");

        // O objeto emailDTO é convertido em JSON e enviado para a fila
        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }

}
