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
			FileReader file = new FileReader("WebJam Application.csv");
			CSVReader<String[]> csvParser = CSVReaderBuilder.newDefaultReader(file);
			List<String[]> persons = csvParser.readAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
