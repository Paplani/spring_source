package ch1;

public class TeamMain {
    public static void main(String[] args) {
        // 팀 만들기
        Team team1 = new Team(1L, "1팀");

        // 맴버 정의
        TeamMember member1 = new TeamMember(1L, "홍길동", team1);
        TeamMember member2 = new TeamMember(2L, "성춘향", team1);

        System.out.println(member1);
        System.out.println(member2);

        System.out.println(member1.getTeam());
        System.out.println(member1.getTeam().getName());

        // 팀 입장에서 우리의 팀원은 누구인가?
        team1.getTeamMember().add(member1);
        team1.getTeamMember().add(member2);
        System.out.println(team1.getTeamMember());
    }
}
