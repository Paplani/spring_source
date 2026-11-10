package ch1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = "team")
public class TeamMember {
    private Long id;
    private String name;
    private Team team;
}
