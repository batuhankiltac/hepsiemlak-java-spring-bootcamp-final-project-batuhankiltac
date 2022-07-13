package com.batuhankiltac.emlakburadauser.service;

//import com.batuhankiltac.emlakburadauser.client.PaymentClient;
import com.batuhankiltac.emlakburadauser.converter.UserConverter;
import com.batuhankiltac.emlakburadauser.dto.request.UserRequest;
import com.batuhankiltac.emlakburadauser.dto.response.UserResponse;
import com.batuhankiltac.emlakburadauser.exception.PaymentInvalidException;
import com.batuhankiltac.emlakburadauser.model.Product;
import com.batuhankiltac.emlakburadauser.model.User;
import com.batuhankiltac.emlakburadauser.repository.ProductRepository;
import com.batuhankiltac.emlakburadauser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final ProductRepository productRepository;

    public List<UserResponse> getAll() {
        List<UserResponse> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            users.add(userConverter.toDto(user));
        }
        log.info("Listed all users.");
        return users;
    }

    public UserResponse getById(Long id) {
        try {
            return userConverter.toDto(userRepository.getById(id));
        } catch (Exception exception) {
            exception.getMessage();
            return null;
        }
    }

    public List<Product> getPackagesByUserId(Long userId) {
        User user = userRepository.getById(userId);
        return new ArrayList<>(user.getProducts());
    }

    public Object getIdIfQuantityExist(Long userId) {
        User user = userRepository.getById(userId);

        if (user.getUserQuantity() > 0) {
            user.setUserQuantity(user.getUserQuantity() - 1);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public UserResponse add(UserRequest userRequest) {
        User user = userConverter.toEntity(userRequest);
        log.info("User has been created.");
        return userConverter.toDto(userRepository.save(user));
    }

    public UserResponse update(UserRequest userRequest) {
        userRepository.findById(userRequest.getId());
        User user = User.builder()
                .id(userRequest.getId())
                .userType(userRequest.getUserType())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .build();
        log.info("User has been updated.");
        return userConverter.toDto(userRepository.save(user));
    }

    public UserResponse updateQuantitiesAndDates(Long userId, Long productId) {
        User user = userRepository.getById(userId);
        Product product = productRepository.getById(productId);

        if (user.getId() != null) {
            Date expired = new Date(user.getPackageExpiredDate().getTime() + (1000L * 60 * 60 * 24 * 30));
            user.setPackageStartedDate(user.getPackageStartedDate());
            user.setPackageExpiredDate(expired);

            user.setUserQuantity(user.getUserQuantity() + product.getQuantity());

            product.addUserToProduct(user);
            user.addProductToUser(product);
            log.info("Package has been added to User.");
            return userConverter.toDto(userRepository.save(user));
        } else {
            throw new PaymentInvalidException("Payment Invalid!");
        }
    }

    public void deleteById(Long id) {
        getById(id);
        userRepository.deleteById(id);
        log.info("User has been deleted.");
    }
}