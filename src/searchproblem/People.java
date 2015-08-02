package searchproblem;

import java.util.HashMap;
import java.util.Map;

public class People {
    
    private static Map<String, String> people;
    
    public People() {
        people = new HashMap<>();
    }
        
    public void addPeopleAndOrigin(String person, String origin) {
        people.put(person, origin);
    }
    
    public String getOriginGivenPerson(String person) {
        return people.get(person);
    }
    
}
