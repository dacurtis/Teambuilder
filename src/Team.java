import java.util.ArrayList;


public class Team {
	ArrayList<Person> team;
	
	Team() {
		team = new ArrayList<Person>();
	}
	
	void addTeamMember(Person p) {
		team.add(p);
	}
}
