package com.batuhankiltac.emlakburadauser.repository;

import com.batuhankiltac.emlakburadauser.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}