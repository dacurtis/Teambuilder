import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.reader.CSVEntryParser;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;

public class Main {
    public static void main(String[] args) {
        try {
        	FileReader file = new FileReader(args[0]);
        	CSVReaderBuilder<String[]> changedDefaults = new CSVReaderBuilder<String[]>(file).strategy(CSVStrategy.UK_DEFAULT);
			CSVReader<String[]> csvParser = changedDefaults.build();
			
			//List of String[], at index 0 of the String[], it will contain all
			//the information in a row. So each String[0] is equavialent to a row
			//in the spreadsheet.
			List<String[]> fileContents = csvParser.readAll();
			
			//DEBUGGING PURPOSES: DELETE AFTERWARDS.
			for (String[] strings : fileContents) {
				System.out.println(strings[0]);
			}
			
			//Need to call parser method to parse information.
			Parser toParse = new Parser(fileContents);
			
			ArrayList<Person> peopleToSort = toParse.parseForPeople();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
