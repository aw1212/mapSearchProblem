package searchproblem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class runSearch {
    
    private static Connections c = new Connections();
    private static People ppl = new People();
    private static int currentTime = 0;
    private static final int MAX_DEPTH = 3;
    private static final int MAX_TIME = 5;
    
    public static void init() {
        
        c.addDirectConnection("Babe", "Jabari", 10);
        c.addBranchingConnections("Caddie", "Zachariah", "Nada", 6, 4);
        c.addBranchingConnections("Dabney", "Rachael", "Ian", 9, 4);
        c.addBranchingConnections("Ean", "Pablo", "Tab", 10, 3);
        c.addBranchingConnections("Fabian", "Nada", "Zachariah", 9, 6);
        c.addBranchingConnections("Gabe", "Tab", "Xander", 9, 9);
        c.addBranchingConnections("Hadassah", "Zachariah", "Ean", 4, 10);
        c.addBranchingConnections("Ian", "Xander", "Lacey", 10, 6);
        c.addBranchingConnections("Jabari", "Oakley", "Nada", 5, 10);
        c.addBranchingConnections("Kaaren", "Gabe", "Pablo", 9, 6);
        c.addBranchingConnections("Lacey", "Jabari", "Kaaren", 7, 6);
        c.addBranchingConnections("Mabel", "Dabney", "Ian", 9, 3);
        c.addBranchingConnections("Nada", "Caddie", "Gabe", 4, 3);
        c.addBranchingConnections("Oakley", "Mabel", "Kaaren", 7, 7);
        c.addBranchingConnections("Pablo", "Yaakov", "Vada", 7, 1);
        c.addBranchingConnections("Qiana", "Oakley", "Yaakov", 10, 2);
        c.addBranchingConnections("Rachael", "Xander", "Hadassah", 6, 8);
        c.addDirectConnection("Sabastian", "Ian", 9);
        c.addBranchingConnections("Tab", "Dabney", "Caddie", 6, 9);
        c.addBranchingConnections("Ula", "Yaakov", "Nada", 5, 4);
        c.addBranchingConnections("Vada", "Gabe", "Tab", 6, 7);
        c.addBranchingConnections("Wade", "Kaaren", "Yaakov", 4, 5);
        c.addBranchingConnections("Xander", "Kaaren", "Rachael", 8, 2);
        c.addDirectConnection("Zachariah", "Vada", 6);
        c.addDirectConnection("Aaden", "Sabastian", 5);
        c.addDirectConnection("Yaakov", "Xander", 5);
        //System.out.println("all connections" + c.getInitializedConnections());
        
        ppl.addPeopleAndOrigin("Zim", "Aaden");
        ppl.addPeopleAndOrigin("Gir", "Mabel");

    }
            
    public static void dfs(String person1, String person2) {
        init();
        Stack<String> unvisitedPlaces = new Stack<>();
        Queue visitedPlaces = new LinkedList();
        String destination = null;
        boolean reachedEndPoint = false;
        unvisitedPlaces.add(ppl.getOriginGivenPerson(person1));
        while(!reachedEndPoint && !unvisitedPlaces.empty()) {
            System.out.println("Unvisited places: " + unvisitedPlaces);
            System.out.println("Visited places: " + visitedPlaces);
            String currentPlace = unvisitedPlaces.pop();
            if (!visitedPlaces.contains(currentPlace)) {
                visitedPlaces.add(currentPlace);
                unvisitedPlaces.addAll(c.getDestinations(currentPlace));
                destination = currentPlace;
            }
            if (destination.equals("Mabel")) {
                reachedEndPoint = true;
                System.out.println("I've reached my destination!");
            }
        }
        if (!reachedEndPoint) {
            System.out.println("I could not reach my destination");
        }
        System.out.print("Visited path: " + visitedPlaces);
    }
       
    public static Queue shortestFullPathSearch(String person, int max, String type) {
        init();
        Queue places = new LinkedList();
        String origin = ppl.getOriginGivenPerson(person);
        String destination;
        int count = 0;
        try {
            places.add(origin);
            while (count < max) {
                if (c.hasMultipleConnections(origin)) {
                    destination = c.getClosestDestinationGivenOrigin(origin);
                }
                else {
                    destination = c.getOnlyDestinationGivenOrigin(origin);                    
                }
                if (places.contains(destination)) {
                    break;
                }
                if (type.equals("depth")) {
                    if ((count + 1) <= MAX_DEPTH) {
                        places.add(destination);
                        origin = destination;
                        count += 1; 
                    }
                }
                if (type.equals("time")) {
                    int time = c.getTimeGivenOriginAndDestination(origin, destination);
                    if ((time + currentTime) <= MAX_TIME) {
                        places.add(destination);
                        origin = destination;
                        currentTime += time;
                    }
                    count += time;
                }
            }
        }
        catch (NullPointerException e) {
            System.out.println(e);
        }
        System.out.println("Places" + places + "\nRoute: ");
        places.stream().map((object) -> (String) object).forEach((place) -> {
            System.out.println("Location: " + place);
        });
        return places;
    } 
    
    public static Queue getShortestCompletePathGivenPersonUnderCertainDepth(String person) {
        return shortestFullPathSearch(person, MAX_DEPTH, "depth");
    }

    public static Queue getShortestCompletePathGivenPersonUnderCertainTime(String person) {
        return shortestFullPathSearch(person, MAX_TIME, "time");
    }
       
    public static void main(String[] args) {
        //getShortestCompletePathGivenPersonUnderCertainTime("Zim");
        //getShortestCompletePathGivenPersonUnderCertainDepth("Zim");
        dfs("Zim", "Gir");
    }
    
}
