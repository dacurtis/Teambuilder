import java.util.ArrayList;


public     class Team{
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
            Sorter.sortList(members);
        }
    }
