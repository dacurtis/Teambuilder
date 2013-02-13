import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Parser {
	
	public List fileToParse;
	
	public Parser(List fileToParse) {
		this.fileToParse = fileToParse;
	}
	
	public ArrayList<Person> parseForPeople() {
		ArrayList<Person> arrayOfPeople = new ArrayList<Person>();
		
		//Making the assumption that the 2nd column in the cvs file is the
		//name, with the 3rd column being the email address.
		
		//TODO: Index 0 of each String[] in fileToParse represents a row.
		//Split based on commas (each comma is a column). Get the necessary
		//information, create a person, add that person to the list.
		
		//Things to look into: Determining if the comma is referring to the 
		//column split or the comma in the column.
		return arrayOfPeople;
	}
}
