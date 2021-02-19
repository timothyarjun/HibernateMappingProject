package com.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.project.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
