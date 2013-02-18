import java.util.ArrayList;

//Returns an ArrayList of teams.
public class TeamBuilder {
	ArrayList<Team> teams;
	int numberOfTeams;
	
	public TeamBuilder(int numberOfTeams) {
		teams = new ArrayList<Team>();
		this.numberOfTeams = numberOfTeams;
	}
	
	public void makeTeams(ArrayList<Person> people) {
		
	}
	
	//Need the list for eventually writing out to a file.
	public ArrayList<Team> getTeams() {
		return teams;
	}
}
