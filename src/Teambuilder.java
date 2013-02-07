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
			List<String[]> persons = csvParser.readAll();
			
			for (String [] row : persons) {
				System.out.println(row[0]);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
