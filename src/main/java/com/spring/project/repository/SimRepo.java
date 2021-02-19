package com.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.project.entity.SimCard;
@Repository
public interface SimRepo extends JpaRepository<SimCard, Long> {

}
