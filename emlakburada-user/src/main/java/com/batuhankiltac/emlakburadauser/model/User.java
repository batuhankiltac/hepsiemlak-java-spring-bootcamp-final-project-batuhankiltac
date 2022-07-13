package com.batuhankiltac.emlakburadauser.model;

import com.batuhankiltac.emlakburadauser.model.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.*;

@Getter
@Setter
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