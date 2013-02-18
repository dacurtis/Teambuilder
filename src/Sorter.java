
import java.util.ArrayList;


public class Sorter {
    private ArrayList<Person> people;
    private ArrayList<Team> teams;
    private int numTeams;
    private int totalScore;
    private int teamScore;
    private int peoplePerTeam;
    
    public Sorter(ArrayList<Person> people, int teams){
        this.people = people;
        numTeams = teams;
        peoplePerTeam = people.size()/numTeams;
        int total = 0;
        for (Person person : people){
            total+= person.getRanking();
        }
        sort(people);
        totalScore = total;
        teamScore = totalScore/numTeams;
    }
    private void sort(ArrayList<Person> people){
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
    class Network extends Person{
        Network network;
        Person self;
        int value;
        int size;
        public Network(Person p){
            super(p.getName(), p.getEmail(), p.getRanking());
            self = p;
            network  = null;
            size = 1;
            value = p.ranking;
        }
        public void addToNetwork(Person p){
            Network n = new Network(p);
            n.network = network;
            network = n;
            value += p.ranking;
            size += 1;
        }
        //Can leave null networks, not done
//        public Person removeFromNetwork(int points){
//            if (network == null)
//                return null;
//            if (ranking == points){
//                Person toReturn = self;
//                self = network;
//            }
//        }
    }
    class Team{
        ArrayList<Person> members;
        int value;
        int leaders;
        ArrayList<Integer> network;
        public Team(){
            members = new ArrayList<Person>();
            value = 0;
            leaders = 0;
        }
        public void addMember(Person person){
            members.add(person);
            value += person.getRanking();
            if (person.isLeader())
                leaders++;
        }
        public Person removeMember(String person){
            for (int i = 0; i < members.size();i++){
                Person member = members.get(i);
                if (member.getName().equals(person)){
                    value -= member.getRanking();
                    if (member.isLeader())
                        leaders--;
                    members.remove(i);
                    return member;
                }   
            }
            return null;
        }
        public void unravelNetworks() {
            for (int i = 0; i < members.size(); i++) {
                Person member = members.get(i);
                if (member instanceof Network) {
                    Network net = (Network) member;
                    while (net.self != null) {
                        members.add(net.self);
                        net = net.network;
                    }
                    members.remove(i);
                }
            }
            sort(members);
        }
    }
    
}
