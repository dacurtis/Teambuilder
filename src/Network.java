    public class Network extends Person{
        Network network;
        Person self;
        int value;
        int size;
        Network previous;
        public Network(Person p){
            super(p.getName(), p.getEmail(), p.getRanking());
            self = p;
            network  = null;
            size = 1;
            value = p.ranking;
            previous = null;
        }
        public void addToNetwork(Person p){
            Network n = new Network(p);
            n.previous = this;
            n.network = network;
            network = n;
            value += p.ranking;
            size += 1;
        }
//        Can leave null networks, not done
//        public Person removeFromNetwork(int points){
//            if (network == null && self != points)
//                return null;
//   
//            if (ranking == points){
//                Person toReturn = self;
//                if (previous != null)
//                    previous.network = network;    
//            }
//        }
    }
