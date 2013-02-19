
import au.com.bytecode.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David Curtis
 */
public class Writer {
    ArrayList<Team> teams;
    String path;
    public Writer(ArrayList<Team> t, String p){
        teams = t;
        path = p;
    }
    public void write() throws IOException{
        CSVWriter writer = new CSVWriter(new FileWriter(new File(path)));
        List<String[]> toWrite = new ArrayList<String[]>();
        for(int i =0; i < teams.size();i++){
            String[] header = new String[1];
            header[0] = "Team " + (i+1) + " Total Score: " + teams.get(i).value + "\n";
            toWrite.add(header);             
            for(Person member :  teams.get(i).members){
                String[] toAppend = new String[3];
                toAppend[0]= member.name;
                toAppend[1] = member.email;
                toAppend[2] = member.ranking + "";
                toWrite.add(toAppend);
            }
            String[] spacer = new String[1];
            spacer[0] = "\n";
            toWrite.add(spacer);
        }
        writer.writeAll(toWrite);
        writer.close();
    }
    
}
