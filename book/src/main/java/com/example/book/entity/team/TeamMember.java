package com.example.book.entity.team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@Entity
// @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "team")
public class TeamMember {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_name")
    private String name;

    // 테이블명_pk명 (default)
    @JoinColumn(name = "team_id") // 해당이름으로 fk 지정
    @ManyToOne
    private Team team;

    //fetch = FetchType.EAGER    =>  즉시  => left join
    //fetch = FetchType.LAZY     =>  게으른 (필요한 시점)  => 

    // 팀 변경하는 메소드
    public void changeTeam(Team team) {
        this.team = team;
    }
}
