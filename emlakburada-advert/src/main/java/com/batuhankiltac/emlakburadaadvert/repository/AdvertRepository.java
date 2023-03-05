package com.batuhankiltac.emlakburadaadvert.repository;

import com.batuhankiltac.emlakburadaadvert.domain.Advert;
import com.batuhankiltac.emlakburadaadvert.domain.enums.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {
    List<Advert> getAllByUserId(Long userId);
    List<Advert> findAllByUserIdAndStatusType(Long userId, StatusType statusType);
}