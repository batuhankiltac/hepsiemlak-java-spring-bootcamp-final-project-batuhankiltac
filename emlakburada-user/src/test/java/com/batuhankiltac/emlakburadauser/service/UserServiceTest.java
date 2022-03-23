package com.batuhankiltac.emlakburadauser.service;

import com.batuhankiltac.emlakburadauser.dto.request.UserRequest;
import com.batuhankiltac.emlakburadauser.dto.response.UserResponse;
import com.batuhankiltac.emlakburadauser.mapper.UserMapper;
import com.batuhankiltac.emlakburadauser.model.User;
import com.batuhankiltac.emlakburadauser.model.enums.UserType;
import com.batuhankiltac.emlakburadauser.repository.ProductRepository;
import com.batuhankiltac.emlakburadauser.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    void addTest() {
        UserRequest userRequest = prepareUserRequest();
        Optional<User> user = Optional.of(prepareUser());

        Mockito
                .when(userRepository.findById(userRequest.getId()))
                .thenReturn(user);

        Mockito
                .when(userRepository.save(any()))
                .thenReturn(prepareUser());

        Mockito
                .when(userMapper.toDto(prepareUser()))
                .thenReturn(prepareUserResponse());

        UserResponse userResponse = userService.add(userRequest);
        assertThat(userResponse);
    }

    private UserRequest prepareUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(1L);
        userRequest.setUserType(UserType.INDIVIDUAL);
        userRequest.setName("test-name");
        userRequest.setEmail("batuhan@gmail.com");
        return userRequest;
    }

    private User prepareUser() {
        User user = new User();
        user.setId(1L);
        user.setUserType(UserType.INDIVIDUAL);
        user.setName("Batuhan");
        user.setEmail("batuhan@gmail.com");
        return user;
    }

    @Test
    void getAllUsersTest() {
        List<UserResponse> responseList = userService.getAll();

        Mockito
                .when(userRepository.findAll())
                .thenReturn(prepareUserList());


        Mockito
                .when(userMapper.toDto(prepareUser()))
                .thenReturn(prepareUserResponse());

        assertEquals(0, responseList.size());
        assertThat(responseList.size());

        for (UserResponse response : responseList) {
            assertThat(response.getName());
            assertEquals("test-name", response.getName());
            assertEquals("batuhan@gmail.com", response.getEmail());
        }
    }

    private List<User> prepareUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(prepareUser());
        return userList;
    }

    private UserResponse prepareUserResponse() {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserType(UserType.INDIVIDUAL);
        userResponse.setName("test-name");
        userResponse.setEmail("batuhan@gmail.com");
        userResponse.setBio("1990");
        return userResponse;
    }

    @Test
    void deleteUserByIdTest() {
        Mockito
                .doNothing().when(userRepository)
                .deleteById(1L);

        Mockito
                .when(userRepository.findById(1L))
                .thenReturn(Optional.of(prepareUser()));

        userService.deleteById(1L);
        verify(userRepository).deleteById(1L);
    }
}