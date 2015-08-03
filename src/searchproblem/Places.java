package searchproblem;

import java.util.HashSet;
import java.util.Set;


public class Places {
    
    private static Set<String> places;
    private static int length;
    
    public Places() {
        places = new HashSet<>();
    }
    
    public void addPlace(String... places) {
        for (String place : places) {
            this.places.add(place);
        }
    }

    public Set<String> getPlaces() {
        return places;
    }
            
    public int getLength() {
        return places.size();
    }
    
}
