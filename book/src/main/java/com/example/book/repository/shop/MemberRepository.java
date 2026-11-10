package com.example.book.repository.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.entity.shop.Member;

public interface  MemberRepository extends JpaRepository<Member, Long>{
    
}
