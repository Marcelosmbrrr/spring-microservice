package com.ms.user.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Jackson2JsonMessageConverter é um conversor de mensagens usado para serializar objetos Java em formato JSON nas mensagens enviadas e recebidas do RabbitMQ
// O RabbitTemplate, por padrão, usa serialização Java binária ao enviar e receber objetos e essa configuração assegura que utilize JSON

@Configuration
public class RabbitMQConfig {

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
