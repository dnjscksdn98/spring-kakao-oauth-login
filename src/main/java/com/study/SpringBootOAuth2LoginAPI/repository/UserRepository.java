package com.study.SpringBootOAuth2LoginAPI.repository;

import com.study.SpringBootOAuth2LoginAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
