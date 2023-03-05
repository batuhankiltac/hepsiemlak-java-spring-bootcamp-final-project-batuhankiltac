package com.batuhankiltac.emlakburadauser.model.response;

import com.batuhankiltac.emlakburadauser.domain.Message;
import com.batuhankiltac.emlakburadauser.domain.Product;
import com.batuhankiltac.emlakburadauser.domain.enums.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private UserType userType;
    private String name;
    private String email;
    private String password;
    private String photo;
    private String bio;
    private String province;
    private String district;
    private Integer quantity;
    private LocalDateTime startedDate;
    private LocalDateTime expiredDate;
    private List<Message> messages;
    private Set<Product> products;
}