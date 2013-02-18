import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
	
	public List<String[]> fileToParse;
	
	public Parser(List<String[]> fileToParse) {
		this.fileToParse = fileToParse;
	}
	
	public ArrayList<Person> parseForPeople() {
		ArrayList<Person> arrayOfPeople = new ArrayList<Person>();
		
		//Making the assumption that the 2nd column in the csv file is the
		//name, with the 3rd column being the email address.
		//Trim removes the leading and trailing whitespace, if any. 
		for (int i = 1; i < fileToParse.size(); i++) {
			String[] containsRow = fileToParse.get(i);
			
			Scanner scanRow = new Scanner(containsRow[0]);  
			scanRow.useDelimiter(",");
			
			//Skips over the timestamp.
			scanRow.next();
			String name = scanRow.next();
			name = name.trim();
			
			String email = scanRow.next();
			email = email.trim();
			
			//temporarily setting ranking to 0.
			int ranking = 0;
			
			Person personToAdd = new Person(name, email, ranking);
			arrayOfPeople.add(personToAdd);
		}
		
		//Things to look into: Determining if the comma is referring to the 
		//column split or the comma in the column.
		return arrayOfPeople;
	}
}
