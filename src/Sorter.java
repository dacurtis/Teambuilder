
import java.util.ArrayList;


public class Sorter {
    private ArrayList<Person> people;
    private ArrayList<Team> teams;
    private int numTeams;
    private int totalScore;
    private int teamScore;
    private int peoplePerTeam;
    
    public Sorter(ArrayList<Person> people, int numTeams){
        this.people = people;
        this.numTeams = numTeams;
        peoplePerTeam = people.size()/numTeams;
        int total = 0;
        for (Person person : people){
            total+= person.getRanking();
        }
        sortList(people);
        totalScore = total;
        teamScore = totalScore/numTeams;
        teams = new ArrayList<Team>();
        for(int i = 0; i < numTeams;i++){
            teams.add(new Team());
        }
    }
    public static void sortList(ArrayList<Person> people){
        ArrayList<Person> sorted = new ArrayList<Person>();
        for(int i = 0; i < people.size();i++){
            for (int sortedPointer = 0; sortedPointer < sorted.size();sortedPointer++){
                if (sorted.get(sortedPointer).getRanking() < people.get(i).getRanking()){   
                    sorted.add(sortedPointer,people.get(i));
                    break;
                }
            }
            sorted.add(people.get(i));
        }
        people = sorted;
    }
    public ArrayList<Team> makeTeams(){
        int counter = 0;
        int direction = 1;
        boolean flipped = true;
        //Makes initial teams, should be relatively fair based on snaking pattern
        for(Person person : people){
            teams.get(counter).addMember(person);
            if ((counter == numTeams - 1 || counter == 0) && !flipped){
                direction *= -1;
                flipped = true;
            }
            else{
                counter += direction;
                flipped = false;
            }
        }
        //step two: make networks
        
        
        
        return teams;
    }
    
}
