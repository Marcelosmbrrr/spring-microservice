package com.ms.user.services;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    /**
     * Salva um usuário no banco de dados
     * Após salvar, publica uma mensagem para o envio de e-mail.
     *
     */
    @Transactional
    public UserModel save(UserModel userModel) {

        userModel = userRepository.save(userModel);

        // Publica uma mensagem para o envio de e-mail.
        userProducer.publishMessageEmail(userModel);

        return userModel;
    }


}
