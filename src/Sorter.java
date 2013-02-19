
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
    private void sortTeams() {
        ArrayList<Team> sorted = new ArrayList<Team>();
        for (int i = 0; i < teams.size(); i++) {
            for (int sortedPointer = 0; sortedPointer < sorted.size(); sortedPointer++) {
                if (sorted.get(sortedPointer).value < teams.get(i).value) {
                    sorted.add(sortedPointer, teams.get(i));
                    break;
                }
            }
            sorted.add(teams.get(i));
        }
        teams = sorted;
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
        sortTeams();
        //step two: make networks
        
        
        //step 3: make swaps

        while (!isDone()) {
            Team highest = teams.get(0);
            Team lowest = teams.get(teams.size() - 1);
            for (int i = 0; i < teams.size() - 1; i++) {

            }
        }
        
        
        return teams;
    }
    private boolean softSwap(Team highest, Team lowest) {
        int difference = highest.value - lowest.value;
        int advantage = highest.members.size() - lowest.members.size();
//        int hStart = 0;
//        int lStart = 0;
//        //don't swap away all of a team's leaders
//        if (highest.leaders == 1)
//            hStart = 1;
//        if (lowest.leaders == 1)
//            lStart = 1;
        
        //Maps scores to possible groups of members
        HashMap highMap = new HashMap();
        HashMap lowMap = new HashMap();
        for (Person member: lowest.members){
            lowMap.put(member.ranking, member);
        }
        for (Person member: highest.members){
            highMap.put(member.ranking, member);
        }
        for(Object key : highMap.keySet()){
            Integer keyVal = (Integer) key;
        }
        //look through tables, try to find an instance where we can perform a valid swap, would be faster to do backwards
        //the higher valued team has more people
        /*
        for (int i = 0; i < highTable.length; i++) 
            for (int j = 0; j < highTable[i].length; j++) 
                for (int x = i + 1; x < lowTable.length && x < i + difference/2 + 1; x++) 
                    for (int y = 0; y < j + advantage; y++) 
                        if(highTable[i][j] && lowTable[x][y]){
                            //Target found! now we have to do the swapping
                            
                    }
         * 
         */

        return false;
    }

    private boolean isDone(){
        for (Team team : teams){
            if(team.value != teamScore && team.value != teamScore + 1)
                return false;
        }
        return true;
    }
    class Group{
        ArrayList<Person> group;
        
    }
    
}
