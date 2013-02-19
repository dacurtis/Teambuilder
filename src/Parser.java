import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import au.com.bytecode.opencsv.CSVReader;

public class Parser {
	
	public List<String[]> fileContents;
	
	public Parser(List<String[]> fileContents) {
		this.fileContents = fileContents;
	}
	
	public ArrayList<Person> parseForPeople() {
		ArrayList<Person> arrayOfPeople = new ArrayList<Person>();
		
		//Making the assumption that the 2nd column in the csv file is the
		//name, with the 3rd column being the email address.
		//Trim removes the leading and trailing whitespace, if any. 
		for(int i = 1; i < fileContents.size(); i++){
			String[] row = fileContents.get(i);
			
			String name = row[1];
			String email = row[2];
			
			String stringRanking = row[row.length - 1];
			int ranking = checkRanking(stringRanking);
			
			Person personToAdd = new Person(name, email, ranking);
			arrayOfPeople.add(personToAdd);
		}
		
		return arrayOfPeople;
	}
	
	private int findColumnWithRequestedPeople(){
		return 0;
	}
	
	private int checkRanking(String rankToCheck){
		//Keep the ranking of of 0 if the temp value isn't a ranking or is 0.
		try{
			return Integer.parseInt(rankToCheck);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
