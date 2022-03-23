package com.batuhankiltac.emlakburadabanner.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "banners")
@Builder
@EqualsAndHashCode
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long advertNo;

    private String title;
    private String phone;
    private Integer quantity;

    @OneToOne
    private Address address;
}