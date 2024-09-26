package com.ms.email.consumers;

import com.ms.email.dtos.EmailRecordDto;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

// Consumer é a classe utilizada para consumir mensagens do message broker

@Component
public class EmailConsumer {

    final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Método que escuta a fila de emails e processa as mensagens recebidas.
     * @RabbitListener escuta mensagens que chegam na fila especificada
     * Quando uma mensagem é recebida, suas propriedades são copiadas para o objeto emailRecordDTO
     * O email é enviado usando o serviço de email.
     *
     */
    @RabbitListener(queues = "${broker.queue.email.name}" )
    public void listenEmailQueue(@Payload EmailRecordDto emailRecordDto) {
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(emailRecordDto, emailModel);
        emailService.sendEmail(emailModel);
    }

}
