import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

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
	HashSet<String> requestedPeople;
        HashSet<Person> network;
    boolean isLeader;
	
	public Person(String name, String email,  int ranking) {
		this.name = name;
		this. email = email;
		this.ranking = ranking;
                if (ranking > 4)
                    isLeader = true;
		this.requestedPeople = new HashSet<String>();
                network = new HashSet<Person>();
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
	
	public HashSet<String> getRequested(){
		return requestedPeople;
	}
	public String getEmail(){
            return email;
        }
	public void addRequested(String name){
		requestedPeople.add(name);
	}
	
	//Will possibly be used to see if the request is mutual.
	public boolean hasRequestedPerson(Person p){
            Iterator it = requestedPeople.iterator();
            while (it.hasNext()){
                if(((String)it.next()).toLowerCase().trim().equals(p.name.toLowerCase().trim()))
                        return true;
            }
            return false;
	}
}
