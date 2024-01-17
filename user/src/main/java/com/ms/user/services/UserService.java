package com.ms.user.services;

import com.ms.user.exceptions.EmailAlreadyExistsException;
import com.ms.user.models.UserModel;
import com.ms.user.producer.UserProducer;
import com.ms.user.repositories.UserRepository;
import org.springframework.dao.DataAccessException;
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

    @Transactional
    public UserModel save(UserModel userModel) throws EmailAlreadyExistsException {
        if (userRepository.existsByEmail(userModel.getEmail())) {
            throw new EmailAlreadyExistsException("E-mail já cadastrado na base de dados");
        }

        try {
            userModel = userRepository.save(userModel);
            userProducer.publishMessageEmail(userModel);
            return userModel;
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao salvar o usuário", e);
        }
    }
}
