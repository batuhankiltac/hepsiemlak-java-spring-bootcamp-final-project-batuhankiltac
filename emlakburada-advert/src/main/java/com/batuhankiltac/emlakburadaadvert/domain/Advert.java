package com.batuhankiltac.emlakburadaadvert.domain;

import com.batuhankiltac.emlakburadaadvert.domain.enums.AdvertType;
import com.batuhankiltac.emlakburadaadvert.domain.enums.RealEstateType;
import com.batuhankiltac.emlakburadaadvert.domain.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "adverts")
@Builder
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long advertNo;

    @Enumerated(value = EnumType.STRING)
    private AdvertType advertType;

    @Enumerated(value = EnumType.STRING)
    private RealEstateType realEstateType;

    @Enumerated(value = EnumType.STRING)
    private StatusType statusType = StatusType.IN_REVIEW;

    private String title;
    private String description;
    private String province;
    private String district;
    private Long price;
    private String rooms;
    private Integer size;
    private Integer buildingAge;
    private Integer floor;
    private LocalDateTime createdDate;

    @OneToMany
    private List<Image> images = new ArrayList<>();
}