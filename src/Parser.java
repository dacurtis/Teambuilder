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
		
		//Will be one less than it actually is since reading array indecies
		//(which start at 0).
		int columnWithRequestedNames = findColumnWithRequestedPeople();
		
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
			
			System.out.println(row[columnWithRequestedNames]);
			
			arrayOfPeople.add(personToAdd);
		}
		
		return arrayOfPeople;
	}
	
	private int findColumnWithRequestedPeople(){
		String[] firstRow = fileContents.get(0);
		int i = 0;
		for(; i < firstRow.length; i++){
			if(firstRow[i].equals("List any preferred team member(s) here")){
				break;
			}
		}
		return i;
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
