package com.example.book.entity.team;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
// @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String name;

    // public Team(Long id, String name) {
    // this.id = id;
    // this.name = name;
    // }

    // public void setId(Long id) {
    // this.id = id;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }

    // 팀 멤버를 확인가능한 메소드, 여기서는 기본적으로 LAZY걸려있음
    // OneToMany에서 누가 주인인지 알려줘야함. mappedBy = "team" => team이 주인
    // 자기 테이블에 FK 컬럼이 있는 쪽이 주인
    // mapped by는 주인이 아닌쪽에 붙이고 주인 필드의 이름을 붙임
    @Builder.Default
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private List<TeamMember> teamMember = new ArrayList<>();

    // public void setTeamMember(List<TeamMember> teamMember) {
    // this.teamMember = teamMember;
    // }

}