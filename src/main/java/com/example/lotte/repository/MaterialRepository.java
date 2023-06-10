package com.example.lotte.repository;

import com.example.lotte.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository <Material, Long> {

}
