package com.batuhankiltac.emlakburadauser.dto.response;

import com.batuhankiltac.emlakburadauser.model.Message;
import com.batuhankiltac.emlakburadauser.model.Product;
import com.batuhankiltac.emlakburadauser.model.enums.UserType;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
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
    private Date startedDate;
    private Date expiredDate;
    private List<Message> messages;
    private Set<Product> products;
}