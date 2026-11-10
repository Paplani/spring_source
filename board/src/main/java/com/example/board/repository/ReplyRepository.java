package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.board.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Modifying 
    @Query("DELETE FROM Reply r WHERE r.board.bno = :bno")
    public void deleteByBno(Long bno);
}
