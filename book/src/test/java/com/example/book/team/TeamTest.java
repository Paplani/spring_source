package com.example.book.team;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.book.entity.team.Team;
import com.example.book.entity.team.TeamMember;
import com.example.book.repository.team.TeamMemberRepository;
import com.example.book.repository.team.TeamRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class TeamTest {

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void insert() {
        Team team1 = teamRepository.save(Team.builder().name("team1").build());
        Team team2 = teamRepository.save(Team.builder().name("team2").build());

        teamMemberRepository.save(TeamMember.builder().name("홍길동").team(team1).build());
        teamMemberRepository.save(TeamMember.builder().name("성춘향").team(team2).build());
    };

    @Test
    public void insert2() {

        Team team1 = teamRepository.findById(1L).get();
        Team team2 = teamRepository.findById(2L).get();

        teamMemberRepository.save(TeamMember.builder().name("김지호").team(team1).build());
        teamMemberRepository.save(TeamMember.builder().name("성지호").team(team2).build());
    };

    // Read
    @Test
    public void read() {

        // 기본적으로 left_join
        TeamMember member1 = teamMemberRepository.findById(1L).get();
        System.out.println(member1);

        // team 정보 조회
        System.out.println(member1.getTeam().getName());

    }

    // @Transactional
    @Test
    public void read2() {

        // LazyInitializationException
        // @OneToMany : 기본이 현재 LAZY
        Team team = teamRepository.findById(1L).get();
        System.out.println(team);

    }

    // Update
    @Test
    public void update() {
        // 4번 맴버의 팀을 2번으로 변경, findById 쓰면 자동으로 left join 걸림
        TeamMember member = teamMemberRepository.findById(4L).get();

        Team team = teamRepository.findById(1L).get();
        member.changeTeam(team);
        teamMemberRepository.save(member);
    }

    // Delete
    @Test
    public void delete() {
        teamMemberRepository.deleteById(3L);
    }

    @Test
    public void delete2() {
        // 2번 팀 삭제
        teamRepository.deleteById(2L);
    }
}
