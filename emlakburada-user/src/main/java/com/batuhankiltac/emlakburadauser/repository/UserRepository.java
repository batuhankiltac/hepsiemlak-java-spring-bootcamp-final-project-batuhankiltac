package com.batuhankiltac.emlakburadauser.repository;

import com.batuhankiltac.emlakburadauser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}