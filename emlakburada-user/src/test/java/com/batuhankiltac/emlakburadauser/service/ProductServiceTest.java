package com.batuhankiltac.emlakburadauser.service;

import com.batuhankiltac.emlakburadauser.converter.ProductConverter;
import com.batuhankiltac.emlakburadauser.domain.Product;
import com.batuhankiltac.emlakburadauser.model.request.ProductRequest;
import com.batuhankiltac.emlakburadauser.model.response.UserResponse;
import com.batuhankiltac.emlakburadauser.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductConverter productConverter;

    @Mock
    private UserService userService;

    @Test
    void it_should_get_by_id() {
        // Given
        Long id = 1L;
        Product product = Product.builder().build();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // When
        productService.getById(id);

        // Then
        verify(productRepository).findById(id);
    }

    @Test
    void it_should_get_packages_by_user_id() {
        // Given
        Long userId = 1L;
        UserResponse userResponse = UserResponse.builder().build();
        when(userService.getById(userId)).thenReturn(userResponse);

        // When
        productService.getPackagesByUserId(userId);

        // Then
        verify(userService).getById(userId);
    }

    @Test
    void it_should_add() {
        // Given
        ProductRequest productRequest = ProductRequest.builder().build();
        Product product = Product.builder().build();
        when(productConverter.convert(productRequest)).thenReturn(product);

        // When
        productService.add(productRequest);

        // Then
        verify(productConverter).convert(productRequest);
        verify(productRepository).save(product);
    }
}
