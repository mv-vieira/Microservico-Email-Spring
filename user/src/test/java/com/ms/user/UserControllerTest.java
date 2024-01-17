package com.ms.user;

import com.ms.user.controllers.UserController;
import com.ms.user.dtos.UserRecordDto;
import com.ms.user.models.UserModel;
import com.ms.user.producer.UserProducer;
import com.ms.user.repositories.UserRepository;
import com.ms.user.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserProducer userProducer;

    @InjectMocks
    private UserController userController;

    @Test
    public void saveUser_ValidInput_ReturnsCreated() throws Exception {
        // Given
        UserRecordDto userRecordDto = new UserRecordDto("Matheus", "mv_vieira96@hotmail.com");

        UserModel userModel = new UserModel();
        userModel.setUserId(UUID.randomUUID());
        BeanUtils.copyProperties(userRecordDto, userModel);

        // Configurar o mock do UserRepository
        when(userRepository.save(any(UserModel.class))).thenReturn(userModel);

        // Configurar o mock do UserService
        doAnswer(invocation -> {
            UserModel userToSave = invocation.getArgument(0);
            userToSave.setUserId(userModel.getUserId());
            return userToSave;
        }).when(userService).save(any(UserModel.class));

        // When
        ResponseEntity<UserModel> responseEntity = userController.saveUser(userRecordDto);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(userModel, responseEntity.getBody());

        // Verificar se o método save do UserService foi chamado com o objeto correto
        ArgumentCaptor<UserModel> captor = ArgumentCaptor.forClass(UserModel.class);
        verify(userService).save(captor.capture());
        assertEquals(userModel.getUserId(), captor.getValue().getUserId());
        assertEquals("Matheus", captor.getValue().getName());
        assertEquals("mv_vieira96@hotmail.com", captor.getValue().getEmail());

        System.out.println(userModel);


    }

}



