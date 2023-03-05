package com.batuhankiltac.emlakburadabanner.repository;

import com.batuhankiltac.emlakburadabanner.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

}