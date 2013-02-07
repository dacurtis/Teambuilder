/* Person.java
 * 
 * Stores the name, score, and an array of requested People
 * for a single Person
 */

public class Person {
	private String name;
	private int score;
	private String[] requestedPeople;
	
	public Person(String name, int score, String[] requestedPeople)
	{
		this.name = name;
		this.score = score;
		this.requestedPeople = requestedPeople;
	}
	public String getName(){
		return name;
	}
	public int getScore(){
		return score;
	}
	public String[] getRequested(){
		return requestedPeople;
	}
	public void addRequested(String name){
		ensureCapacity(requestedPeople.length +1);
		requestedPeople[requestedPeople.length] =  name;
	}
	
	//Increase size of array of requestedPeople if needed.
	private void ensureCapacity (int minCapacity) {
		int oldCapacity = requestedPeople.length;
		if (minCapacity > oldCapacity) {
			String old[] = requestedPeople;
			int newCapacity = 2*oldCapacity;
			if (newCapacity < minCapacity)
				newCapacity = minCapacity;
			requestedPeople = new String[newCapacity];
			for(int i = 0; i < oldCapacity; i++)
				requestedPeople[i] = old[i];
		}
	}  
}
