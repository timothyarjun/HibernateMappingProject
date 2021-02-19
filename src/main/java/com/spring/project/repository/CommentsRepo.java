package com.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.project.entity.Comments;

public interface CommentsRepo extends JpaRepository<Comments, Long> {

}
