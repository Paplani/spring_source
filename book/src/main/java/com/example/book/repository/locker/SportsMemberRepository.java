package com.example.book.repository.locker;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.entity.locker.SportsMember;

public interface SportsMemberRepository extends JpaRepository<SportsMember, Long> {
    
}
