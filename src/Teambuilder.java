import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;

public class Teambuilder {
    public static void main(String[] args) {
        try {
        	System.out.println(args[0]);
        	FileReader file = new FileReader(args[0]);
			CSVReader<String[]> csvParser = CSVReaderBuilder.newDefaultReader(file);
			
			//List of String[], at index 0 of the String[], it will contain all
			//the information in a row. So each String[0] is equavialent to a row
			//in the spreadsheet.
			List<String[]> fileContents = csvParser.readAll();
			
			//Need to call parser method to parse information.
			
			//Debugging purposes here to see how the csv parser works.
			int i = 1;
			for (String[] strings : fileContents) {
				System.out.println("Row number: " + i);
				//Each row is stored at index 0.
				System.out.println(strings[0]);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
