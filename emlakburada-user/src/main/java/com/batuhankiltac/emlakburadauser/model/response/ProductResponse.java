package com.batuhankiltac.emlakburadauser.model.response;

import com.batuhankiltac.emlakburadauser.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ProductResponse {
    private String name;
    private Integer quantity;
    private LocalDateTime createdDate;
    private LocalDateTime expiredDate;
    private List<User> users;
    private Long orderNo;
    private Long price;
}