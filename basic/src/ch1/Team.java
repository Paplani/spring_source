package ch1;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
// @Setter
@AllArgsConstructor
@ToString(exclude = "teamMember")
public class Team {

    private Long id;
    private String name;

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 팀 멤버를 확인가능한 메소드
    private List<TeamMember> teamMember = new ArrayList<>();

    public void setTeamMember(List<TeamMember> teamMember) {
        this.teamMember = teamMember;
    }

}