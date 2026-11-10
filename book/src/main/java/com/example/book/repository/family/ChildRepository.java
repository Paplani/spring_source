package com.example.book.repository.family;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.entity.family.Child;

public interface ChildRepository extends JpaRepository<Child, Long> {

}
