package com.example.book.repository.team;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.entity.team.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
