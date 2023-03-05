package com.batuhankiltac.emlakburadauser.service;

import com.batuhankiltac.emlakburadauser.converter.UserConverter;
import com.batuhankiltac.emlakburadauser.domain.Product;
import com.batuhankiltac.emlakburadauser.domain.User;
import com.batuhankiltac.emlakburadauser.exception.GlobalException;
import com.batuhankiltac.emlakburadauser.model.request.UserRequest;
import com.batuhankiltac.emlakburadauser.model.response.UserResponse;
import com.batuhankiltac.emlakburadauser.repository.ProductRepository;
import com.batuhankiltac.emlakburadauser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final ProductRepository productRepository;

    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(userConverter::convert)
                .collect(Collectors.toList());
    }

    public UserResponse getById(Long id) {
        return userConverter.convert(userRepository.findById(id).orElseThrow(() -> new GlobalException("User not found.")));
    }

    public UserResponse add(UserRequest userRequest) {
        User user = userConverter.convert(userRequest);
        log.info("User has been created.");
        return userConverter.convert(userRepository.save(user));
    }

    public void update(UserRequest userRequest) {
        User user = userRepository.findById(userRequest.getId()).orElseThrow(() -> new GlobalException("User not found."));
        user.setId(userRequest.getId());
        user.setUserType(userRequest.getUserType());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        log.info("User has been updated.");
        userRepository.save(user);
    }

    public UserResponse updateQuantitiesAndDates(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new GlobalException("User not found."));
        Product product = productRepository.findById(productId).orElseThrow(() -> new GlobalException("Product not found."));

        user.setPackageExpiredDate(LocalDateTime.now().plusDays(30));
        user.setUserQuantity(user.getUserQuantity() + product.getQuantity());

        product.addUserToProduct(user);
        user.addProductToUser(product);
        log.info("Package has been added to User.");
        return userConverter.convert(userRepository.save(user));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
        log.info("User has been deleted.");
    }
}