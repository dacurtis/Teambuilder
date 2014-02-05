
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
        //The lists should always be very small, so the overhead of more complex sorting methods is not necessary
        sortTeams();

        
        //step 3: make swaps
        int count = 100;
        while (!isDone() && count > 0){
            count--;
            sortTeams();
            swapEngine(0,teams.size()-1);
        }
        System.out.println("COunt:" + isDone());
        count = 100;
        while(count > 0){
            if(swapForRequest() == false){
                System.out.println("Broken");
                break;
            }
            else
            count--;
        }
        
        sortTeams();
        for (Team team : teams){
            
            sortList(team.members);
            System.out.println(team.value);
        }
             
        return teams;
    }
    //Tries to swap the most stacked team with the least stacked team, if that doesnt work moves inwards
    private boolean swapEngine(int high, int low){
        Team highest = teams.get(high);
        Team lowest = teams.get(low);
        if (highest.value - lowest.value <= 1)
            return false;
        if(!softSwap(highest,lowest)){
            return swapEngine(high+1,low) || swapEngine(high,low-1);
        }
        return true;
    }
    //Attempts to make swaps, some hard coded stuff should be fixed
    private boolean softSwap(Team highest, Team lowest) {
        int difference = highest.value - lowest.value;
        int advantage = highest.members.size() - lowest.members.size();
        if(difference > 1 && advantage > 0)
            for(Person member : highest.members)
                if(member.getRanking() == 1){
                    lowest.addMember(member);
                    highest.removeMember(member);
                    return true;
                }
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
            System.out.println(Math.ceil(((double)difference)/2));
            for(int i = 1; i < Math.ceil(((double)difference)/2);i++)
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
                for(int i = 1; i < Math.ceil(((double)difference)/2);i++)  
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
                for(int i = 1; i < Math.ceil(((double)difference)/2);i++)
                    if (lowMap.containsKey(key + p.ranking - i)){
                        if(swap(highest,lowest,g,new Group((Person)lowMap.get(key))))
                            return true;
                    }
              }
        else
              for(Object keyVal : highGroup.keySet()){
                Integer key = (Integer) keyVal;
                for(int i = 1; i < Math.ceil(((double)difference)/2);i++)
                    if (lowMap.containsKey(key - i)){
                        if(swap(highest,lowest,(Group)highGroup.get(keyVal),(Group)lowGroup.get(key - i)))
                            return true;
                    }
              }
        return false;
    }
    //Look for groups of friends, swap friends onto same teams if possible without unbalancing teams
    private boolean swapForRequest() {
        for(Team team : teams)
            calcConnections(team);
        for(Team team1 : teams)
            for(int t2 = teams.size()-1; t2 >= 0;t2--){
                if(!team1.equals(teams.get(t2)))
                    for(int i =0;i < team1.members.size();i++){
                        Person member1 = team1.members.get(i);
                        for(int j =0; j < teams.get(t2).members.size();j++){
                            Person member2 = teams.get(t2).members.get(j);
                            if (member1.hasRequestedPerson(member2))
                                //does the work
                                if(requestSwap(team1,teams.get(t2),member1,member2))
                                    return true;
                        }
                    }                            
            }
        return false;
                
            
    }
    private boolean requestSwap(Team team1, Team team2, Person p1, Person p2){
        int advantage = team1.members.size() - team2.members.size();
        if(advantage < 0 ){
            return requestSwap(team2,team1,p2,p1);
        }
        int difference = team1.value - team2.value;
        //make groups
        HashMap team1Groups = formGroups(team1);
        HashMap team2Groups = formGroups(team2);
        for(Object key: team1Groups.keySet()){
            ArrayList<Group> group1s = (ArrayList<Group>) team1Groups.get(key);
            ArrayList<Group> group2s = new ArrayList<Group>();
            if(team2Groups.containsKey(key))
                group2s = (ArrayList<Group>) team2Groups.get(key);               
            if(team2Groups.containsKey(((Integer)key)-difference))
                group2s.addAll((ArrayList<Group>)team2Groups.get(((Integer)key)-difference));
            //There is a list of groups with an acceptable value to trade
            if(group2s != null){
                for(Group group1: group1s)
                    for(Group group2:group2s){
                        //If these two groups are the not the same network
                        boolean mutex = true;
                        for(Person pers1 : group1.group)
                            for(Person pers2:group2.group)
                                if(pers1.hasRequestedPerson(pers2) || pers2.hasRequestedPerson(pers1))
                                    mutex = false;
                        if (mutex)
                            //If these groups are of acceptable sizes
                            if(group2.group.length -group1.group.length == advantage || group2.group.length == group1.group.length){
                                for(Person p: team1.members){
                                    for(Person pers : group2.group)
                                        if(p.hasRequestedPerson(pers)){
                                            if(swap(team1, team2, group1, group2))
                                                return true;
                                            else
                                                break;
                                        }
                                }
                                for(Person p: team2.members){
                                    for(Person pers : group2.group)
                                       if(p.hasRequestedPerson(pers))
                                        if(swap(team1, team2, group1, group2))
                                            return true;
                                        else
                                            break;
                                }  
                            }
                        }
            }              
        }
        return false;
    }
    
    private HashMap formGroups(Team team){
        HashSet<Integer> indices = new HashSet<Integer>();
        ArrayList<Group> groups = new ArrayList<Group>();
        for(int i =0;i < team.members.size();i++){            
            ArrayList<Person> people = new ArrayList<Person>();
            if(!indices.contains(i)){
                people.add(team.members.get(i));
                indices.add(i);
            }
            for(int j = i+1;j<team.members.size();j++){
                if(!indices.contains(j)){
                    if(team.members.get(i).network.contains(team.members.get(j))&& team.members.get(j).network.contains(team.members.get(i))){
                        //they are a group
                        people.add(team.members.get(j));
                        indices.add(j);
                    }                    
                }
            }

            groups.add(new Group(people));
        }
        ArrayList<Group>moreGroups = new ArrayList<Group>();
        for(int i =0; i < groups.size();i++){
            ArrayList<Person>toAddToGroup = new ArrayList<Person>();
                for(Person p : groups.get(i).group)
                    toAddToGroup.add(p);
            for(int j =i;j<groups.size();j++){
                ArrayList<Person>compositeGroup = new ArrayList<Person>(toAddToGroup);
                for(Person p : groups.get(j).group)
                    compositeGroup.add(p);
                Group biggerGroup = new Group(compositeGroup);
                moreGroups.add(biggerGroup);
            }
        }
        HashMap toReturn = new HashMap();
        for(Group group : groups){
            int sum = 0;
            for(Person p : group.group)
                sum+=p.getRanking();
            if(!toReturn.containsKey(sum)){
                ArrayList<Group> g = new ArrayList<Group>();
                g.add(group);
                toReturn.put(sum,g);
            }
            else{
               ((ArrayList<Group>) toReturn.get(sum)).add(group);                 
            }
                
        }
        return toReturn;
    }
    private void calcConnections(Team team){
        for(Person member : team.members){
            member.network.clear();
        }
        
        for(int i =0; i < team.members.size();i++)
            for(int j = team.members.size()-1;j>i;j--){
                if (team.members.get(i).hasRequestedPerson(team.members.get(j)))
                    team.members.get(i).network.add(team.members.get(j));
                if (team.members.get(j).hasRequestedPerson(team.members.get(i)))
                    team.members.get(j).network.add(team.members.get(i));   
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
    //makes the swap if it is valid, returns its validity
    private boolean swap(Team highest, Team lowest, Group highPlayers, Group lowPlayers) {
        int hLeaders = 0;
        int lLeaders = 0;
        //find group leaders, each team must have a leader
        for(int i =0; i < highPlayers.group.length;i++){
            if(highPlayers.group[i].isLeader)
                hLeaders++;
        }
        for(int i =0; i < lowPlayers.group.length;i++){
            if(lowPlayers.group[i].isLeader)
                lLeaders++;
        }
        //If this swap will leave a team without a leader, don't do it
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
            if(team.value != teamScore || team.value != teamScore + 1)
                return false;
        }
        return true;
    }


    class Group {
        Person[] group;
        private Group(Person... people) {
            group = people;
        }
        private Group(ArrayList<Person> people){
            group = people.toArray(new Person[people.size()]);
        }
        
    }
    
}
