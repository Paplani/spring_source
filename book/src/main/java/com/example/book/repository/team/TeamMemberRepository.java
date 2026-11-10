package com.example.book.repository.team;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.entity.team.TeamMember;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

}
