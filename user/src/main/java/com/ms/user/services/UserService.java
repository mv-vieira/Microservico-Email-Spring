package com.ms.user.services;

import com.ms.user.models.UserModel;
import com.ms.user.producer.UserProducer;
import com.ms.user.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer){
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public UserModel save(UserModel userModel){
        userModel = userRepository.save(userModel);
        userProducer.publishMessageEmail(userModel);
        return userModel;
    }
}
