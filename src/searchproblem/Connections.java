package searchproblem;

import java.util.Collections;
import java.util.HashMap;

public class Connections {
    
    private static HashMap<String, HashMap<String, Integer>> connections;
    
    public Connections() {
        connections = new HashMap<>();
    }
    
    public void addDirectConnection(String origin, String destination, int time) {
        HashMap<String, Integer> innerMap = new HashMap<>();    
        innerMap.put(destination, time);
        connections.put(origin, innerMap);
    }
    
    public void addBranchingConnections(String origin, String dest1, String dest2, int time1, int time2) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put(dest1, time1);
        map.put(dest2, time2);
        connections.put(origin, map);
    }
            
    public String getOnlyDestinationGivenOrigin(String origin) {
        String destination = null;
        HashMap<String, Integer> map = connections.get(origin);
        for (HashMap.Entry<String,Integer> entry : map.entrySet()) {
            destination = entry.getKey();
        }
        return destination;
    }
                  
    public String getClosestDestinationGivenOrigin(String origin) {
        String closest = null;
        HashMap<String, Integer> map = connections.get(origin);
        int minValueInMap=(Collections.min(map.values()));  // This will return min value in the Hashmap
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {  // Iterate through hashmap
            if (entry.getValue()==minValueInMap) {
                closest = entry.getKey();     // Print the key with min value
            }
        }
        return closest;
    }
    
    public int getTimeGivenOriginAndDestination(String origin, String destination) {
        int time = 0;
        HashMap<String, Integer> map = connections.get(origin);
        time = map.get(destination);
        return time;
    }
    
    public boolean hasMultipleConnections(String origin) {
        int count = 0;
        HashMap<String, Integer> map = connections.get(origin);
        for (HashMap.Entry<String,Integer> entry : map.entrySet()) {
            count += 1;
        }
        return (count>1);           
    }
    
    public HashMap<String, HashMap<String, Integer>> getInitializedConnections() {
        return connections;
    }
    
}
