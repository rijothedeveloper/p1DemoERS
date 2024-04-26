package com.revature.p1demoERS.dao;

import com.revature.p1demoERS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long>{
    Optional<User> findUserByUsername(String username);

}
