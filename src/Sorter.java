
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author David Curtis
 */
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
        //from wikipedia...because why not
        for (int i = 1; i < people.size(); i++) {
            //The values in A[ i ] are checked in-order, starting at the second one
            // save A[i] to make a hole that will move as elements are shifted
            // the value being checked will be inserted into the hole's final position
            Person personToInsert = people.get(i);
            int holePos = i;
            // keep moving the hole down until the value being checked is larger than 
            // what's just below the hole <!-- until A[holePos - 1] is <= item -->
            while (holePos > 0 && personToInsert.ranking > people.get(holePos - 1).ranking) { //value to insert doesn't belong where the hole currently is, so shift 
                people.set(holePos, people.get(holePos - 1)); //shift the larger value up
                holePos = holePos - 1;       //move the hole position down
            }
            // hole is in the right position, so put value being checked into the hole
            people.set(holePos, personToInsert);
        }
    }
   //insertionsort
//THERE IS A FUCKING BUG HERE SOMEWHERE DAMMIT WIKIPEDIA
    private void sortTeams() {
        //from wikipedia...because why not
        for (int i = 1; i < teams.size(); i++) {
            //The values in A[ i ] are checked in-order, starting at the second one
            // save A[i] to make a hole that will move as elements are shifted
            // the value being checked will be inserted into the hole's final position
            Team teamToInsert = teams.get(i);
            int holePos = i;
            // keep moving the hole down until the value being checked is larger than 
            // what's just below the hole <!-- until A[holePos - 1] is <= item -->
            while (holePos > 0 && teamToInsert.value > teams.get(holePos - 1).value) { //value to insert doesn't belong where the hole currently is, so shift 
                teams.set(holePos, teams.get(holePos - 1)); //shift the larger value up
                holePos = holePos - 1;       //move the hole position down
            }
            // hole is in the right position, so put value being checked into the hole
            teams.set(holePos, teamToInsert);
        }
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
        //insertionsort, so they are all in order
        sortTeams();
        //step two: make networks
        
        
        //step 3: make swaps
        int count = 1000;
        while (!isDone() && count > 0 && swapEngine(0,teams.size()-1));
 
        swapForRequest();
        sortTeams();
        for (Team team : teams){
            
            sortList(team.members);
            System.out.println(team.value);
        }
             
        return teams;
    }
    private boolean swapEngine(int high, int low){
        Team highest = teams.get(high);
        Team lowest = teams.get(low);
        if (highest.value - lowest.value <= 1)
            return false;
        if(!softSwap(highest,lowest)){
            return swapEngine(high+1,low) && swapEngine(high,low-1);
        }
        return true;
    }
    private boolean softSwap(Team highest, Team lowest) {
        int difference = highest.value - lowest.value;
        int advantage = highest.members.size() - lowest.members.size();
        
        //Maps scores to possible groups of members
        HashMap highMap = new HashMap();
        HashMap lowMap = new HashMap();
        for (Person member: lowest.members){
            lowMap.put(member.ranking, member);
        }
        for (Person member: highest.members){
            highMap.put(member.ranking, member);
        }
        for(Object keyVal : lowMap.keySet()){
            Integer key = (Integer) keyVal;
            for(int i = 1; i < difference/2 +1;i++)
                if (highMap.containsKey(key + i)){
                    if(swap(highest,lowest,(Person)highMap.get(key + i),(Person)lowMap.get(key)))
                        return true;
                }               
        }
        //HARD CODING I AM SORRY BUT I DGAF
        //do a 2 for 1 swap 
        HashMap highGroup = new HashMap();
        HashMap lowGroup = new HashMap();
        if(advantage > 0)
            for(Person p : highest.members)
              for(Object keyVal : highMap.keySet()){
                Integer key = (Integer) keyVal;
                if (highMap.get(keyVal).equals(p))
                    continue;
                Group g = new Group((Person)highMap.get(key),p);
                highGroup.put((Integer)key + p.ranking, g);
                for(int i = 1; i < difference/2 +1;i++)  
                    if (lowMap.containsKey(key + p.ranking - i)){
                        if(swap(highest,lowest,g,new Group((Person)lowMap.get(key + p.ranking - i))))
                            return true;
                    }
            }
        else if(advantage < 0)
            for(Person p : highest.members)
              for(Object keyVal : highMap.keySet()){
                Integer key = (Integer) keyVal;
                if (highMap.get(keyVal).equals(p))
                    continue;
                Group g = new Group((Person)highMap.get(key),p);
                lowGroup.put((Integer)key + p.ranking, g);
                for(int i = 1; i < difference/2 +1;i++)
                    if (lowMap.containsKey(key + p.ranking - i)){
                        if(swap(highest,lowest,g,new Group((Person)lowMap.get(key))))
                            return true;
                    }
              }
        else
              for(Object keyVal : highGroup.keySet()){
                Integer key = (Integer) keyVal;
                for(int i = 1; i < difference/2 +1;i++)
                    if (lowMap.containsKey(key - i)){
                        if(swap(highest,lowest,(Group)highGroup.get(keyVal),(Group)lowGroup.get(key - i)))
                            return true;
                    }
              }
        return false;
    }
    private void swapForRequest() {
        for(int i =0; i < teams.size();i++){
            for(int j = 0; j < teams.get(j)){
                
            }
        }
    }
    private boolean swap(Team highest, Team lowest, Person highPlayer, Person lowPlayer) {
        if(highPlayer.isLeader && highest.leaders == 1 && !lowPlayer.isLeader)
            return false;
        lowest.addMember(highPlayer);
        highest.addMember(lowPlayer);
        lowest.removeMember(lowPlayer);
        highest.removeMember(highPlayer);
        return true;
    }
    private boolean swap(Team highest, Team lowest, Group highPlayers, Group lowPlayers) {
        int hLeaders = 0;
        int lLeaders = 0;
        for(int i =0; i < highPlayers.group.length;i++){
            if(highPlayers.group[i].isLeader)
                hLeaders++;
        }
        for(int i =0; i < lowPlayers.group.length;i++){
            if(lowPlayers.group[i].isLeader)
                lLeaders++;
        }
        if (lLeaders + highest.leaders - hLeaders == 0 || hLeaders + lowest.leaders - lLeaders ==0)
                return false;
        for(Person highPlayer: highPlayers.group)
            lowest.addMember(highPlayer);
        for(Person lowPlayer: lowPlayers.group){
            highest.addMember(lowPlayer);
            lowest.removeMember(lowPlayer);
        }
        for(Person highPlayer: highPlayers.group)
            highest.removeMember(highPlayer);

        return true;
    }
    private boolean isDone(){
        for (Team team : teams){
            if(team.value != teamScore && team.value != teamScore + 1)
                return false;
        }
        return true;
    }


    class Group {
        Person[] group;
        private Group(Person... people) {
            group = people;
        }
        
    }
    
}
