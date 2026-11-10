package com.example.book.repository.family;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.entity.family.Parent;

public interface ParentRepository extends JpaRepository<Parent, Long>{
    
}
