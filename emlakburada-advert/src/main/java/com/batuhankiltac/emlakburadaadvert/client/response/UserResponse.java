package com.batuhankiltac.emlakburadaadvert.client.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String name;
    private String email;
    private String photo;
    private String bio;
    private String province;
    private String district;
    private Integer quantities;
}