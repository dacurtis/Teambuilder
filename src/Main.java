import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;

public class Main {
    public static void main(String[] args) {
        try {
        	FileReader file = new FileReader(args[0]);
			CSVReader<String[]> csvParser = CSVReaderBuilder.newDefaultReader(file);
			
			//List of String[], at index 0 of the String[], it will contain all
			//the information in a row. So each String[0] is equavialent to a row
			//in the spreadsheet.
			List<String[]> fileContents = csvParser.readAll();
			
			//Need to call parser method to parse information.
			Parser toParse = new Parser(fileContents);
			
			ArrayList<Person> peopleToSort = toParse.parseForPeople();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
