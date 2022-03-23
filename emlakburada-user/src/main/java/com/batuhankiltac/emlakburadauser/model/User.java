package com.batuhankiltac.emlakburadauser.model;

import com.batuhankiltac.emlakburadauser.model.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    private String name;
    private String email;
    private String password;
    private String photo;
    private String bio;
    private String province;
    private String district;
    private Integer userQuantity = 0;
    private Date packageStartedDate = new Date();
    private Date packageExpiredDate = new Date();

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "users")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Product> products = new HashSet<>();

    @OneToMany
    private List<Message> messages = new ArrayList<>();

    public void addProductToUser(Product product) {
        products.add(product);
    }
}