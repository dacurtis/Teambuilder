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
			
			String name = row[1].trim();
			String email = row[2].trim();
			
			String stringRanking = row[row.length - 1];
			int ranking = checkRanking(stringRanking);
                        if (ranking == 0)
                            continue;
			
			Person personToAdd = new Person(name, email, ranking);
			
			//Parse the names of the requested people and add them.
			String stringOfNames = row[columnWithRequestedNames];
			if (!stringOfNames.equals("")){
				ArrayList<String> namesToAdd = getRequestedNames(stringOfNames);
				for (String nameOfRequested : namesToAdd) {
					personToAdd.addRequested(nameOfRequested);
				}
			}
			
			arrayOfPeople.add(personToAdd);
		}
//		for (Person person : arrayOfPeople)
//                    for(String name : person.getRequested())
//                        for(Person p : arrayOfPeople)
//                            if(p.name.equals(name))
//                                person.network.add(p);
                       
                
		return arrayOfPeople;
	}
	
	//Alternatively, could pass along a person and directly add the requested
	//names directly in here.
	private ArrayList<String> getRequestedNames(String requestedNames){
		ArrayList<String> separatedRequestedNames = new ArrayList<String>();
		Scanner scanForNames = new Scanner(requestedNames);
		
		scanForNames.useDelimiter("\\s*and\\s*|,|\\n|\\.|\\!");
		
		while(scanForNames.hasNext()){
			String nameToAdd = scanForNames.next().trim();
			
			if(!nameToAdd.equals("")){
				separatedRequestedNames.add(nameToAdd);
			}
		}
		
		scanForNames.close();
		
		return separatedRequestedNames;
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
