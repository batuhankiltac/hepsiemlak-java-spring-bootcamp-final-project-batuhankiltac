package com.batuhankiltac.emlakburadauser.service;

import com.batuhankiltac.emlakburadauser.converter.UserConverter;
import com.batuhankiltac.emlakburadauser.domain.User;
import com.batuhankiltac.emlakburadauser.model.request.UserRequest;
import com.batuhankiltac.emlakburadauser.model.response.UserResponse;
import com.batuhankiltac.emlakburadauser.repository.ProductRepository;
import com.batuhankiltac.emlakburadauser.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConverter userConverter;

    @Mock
    private ProductRepository productRepository;

    @Test
    void it_should_get_all() {
        // Given
        User user = User.builder().build();
        UserResponse userResponse = UserResponse.builder().build();
        when(userConverter.convert(user)).thenReturn(userResponse);

        // When
        userService.getAll();

        // Then
        verify(userRepository).findAll();
    }

    @Test
    void it_should_get_by_id() {
        // Given
        Long id = 1L;
        User user = User.builder().build();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        UserResponse userResponse = UserResponse.builder().build();
        when(userConverter.convert(user)).thenReturn(userResponse);

        // When
        userService.getById(id);

        // Then
        verify(userRepository).findById(id);
    }

    @Test
    void it_should_add() {
        // Given
        UserRequest userRequest = UserRequest.builder().build();
        User user = User.builder().build();
        when(userConverter.convert(userRequest)).thenReturn(user);

        // When
        userService.add(userRequest);

        // Then
        verify(userConverter).convert(userRequest);
        verify(userRepository).save(user);
    }

    @Test
    void it_should_update() {
        // Given
        UserRequest userRequest = UserRequest.builder()
                .id(1L)
                .build();
        User user = User.builder()
                .id(userRequest.getId())
                .build();
        when(userRepository.findById(userRequest.getId())).thenReturn(Optional.of(user));

        // When
        userService.update(userRequest);

        // Then
        verify(userRepository).findById(userRequest.getId());
        verify(userRepository).save(user);
    }

    @Test
    void it_delete() {
        // Given
        Long id = 1L;

        // When
        userService.deleteById(id);

        // Then
        verify(userRepository).deleteById(id);
    }
}