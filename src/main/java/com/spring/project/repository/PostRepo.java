package com.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.project.entity.Post;

public interface PostRepo extends JpaRepository<Post, Long> {

}
