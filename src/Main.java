import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;

public class Main {
    public static void main(String[] args) {
        try {
        	FileReader file = new FileReader(args[0]);
        	
        	CSVReader reader = new CSVReader(file);
			
        	List<String[]> fileContents = reader.readAll();
        	
			//Need to call parser method to parse information.
			Parser toParse = new Parser(fileContents);
			
			ArrayList<Person> peopleToSort = toParse.parseForPeople();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
