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
		
		//Need to add in code for adding people's requests.
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
			
			//Temporarily set the ranking to 0 to check for later.
			int ranking = 0;
			
			//Temp will hold the last value in the csv file, this variable will
			//be checked later to see if there is a valid ranking or not. 
			String temp = "";
			
			
			//Get the last value in the csv file.
			do {
				temp = scanRow.next();
			}
			while (scanRow.hasNext());
			
			//Keep the ranking of of 0 if the temp value isn't a ranking or is 0.
			try{
				ranking = Integer.parseInt(temp);
			} catch (NumberFormatException e) {
				
			}
			
			//Don't make a person if the ranking is 0. (Means that the person
			//didn't have a ranking or the ranking was 0, both of which are
			//invalid.)
			if (ranking != 0){
				Person personToAdd = new Person(name, email, ranking);
				arrayOfPeople.add(personToAdd);
			}
		}
		
		//Things to look into: Determining if the comma is referring to the 
		//column split or the comma in the column.
		return arrayOfPeople;
	}
}
