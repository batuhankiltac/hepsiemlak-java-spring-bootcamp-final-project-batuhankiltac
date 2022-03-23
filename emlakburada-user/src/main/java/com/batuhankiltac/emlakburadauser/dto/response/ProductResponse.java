package com.batuhankiltac.emlakburadauser.dto.response;

import com.batuhankiltac.emlakburadauser.model.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProductResponse {
    private String name;
    private Integer quantity;
    private Date createdDate;
    private Date expiredDate;
    private List<User> users;
    private Long orderNo;
    private Long price;
}