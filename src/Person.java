import java.util.ArrayList;

/* Person.java
 * 
 * Stores the name, score, and an array of requested People
 * for a single Person
 */

public class Person 
{
	String name;
	String email;
	int ranking;
	ArrayList<String> requestedPeople;
        boolean isLeader;
	
	public Person(String name, String email,  int ranking) {
		this.name = name;
		this. email = email;
		this.ranking = ranking;
                if (ranking > 4)
                    isLeader = true;
		this.requestedPeople = new ArrayList<String>();
	}
	
	public String getName() {
		return name;
	}
	public boolean isLeader(){
            return isLeader;
        }
	public int getRanking() {
		return ranking;
	}
	
	public ArrayList<String> getRequested(){
		return requestedPeople;
	}
	public String getEmail(){
            return email;
        }
	public void addRequested(String name){
		requestedPeople.add(name);
	}
}
