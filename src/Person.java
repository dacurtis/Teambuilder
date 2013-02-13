import java.util.ArrayList;

/* Person.java
 * 
 * Stores the name, score, and an array of requested People
 * for a single Person
 */

public class Person 
{
	private String name;
	private String email;
	private int ranking;
	private ArrayList<String> requestedPeople;
	
	public Person(String name, String email,  int ranking) {
		this.name = name;
		this. email = email;
		this.ranking = ranking;
		this.requestedPeople = new ArrayList<String>();
	}
	
	public String getName() {
		return name;
	}
	
	public int getRanking() {
		return ranking;
	}
	
	public ArrayList<String> getRequested(){
		return requestedPeople;
	}
	
	public void addRequested(String name){
		requestedPeople.add(name);
	}
}
