package com.alessandra.search;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import static com.alessandra.search.Initializer.init;

public class Search {
    
    private static final int MAX_DEPTH = 3;
    private static final int MAX_TIME = 20;
    private static Connections c = new Connections();
    private static People ppl = new People();
    private Stack<String> unvisitedPlaces = new Stack<>();
    
    //omg this method is so hacky don't judge me!
    public void getShortestPathForPeople(String person1, String person2) {
        Map<String,Integer> person1Map = getAllDestinationsWithTime(person1);
        Map<String,Integer> person2Map = getAllDestinationsWithTime(person2);
        int shortestTime = 10000; //change this it's so hacky!
        String shortestDest = null;
        for (int i = 0; i <= Math.min(person1Map.size(), person2Map.size()); i++) {
            for (String visitedPlace : person1Map.keySet()) { //should really iterate through smaller of two maps but dont wanna add more code
                if (person2Map.keySet().contains(visitedPlace) && (person1Map.get(visitedPlace) + person2Map.get(visitedPlace)) < shortestTime) {
                    shortestTime = person1Map.get(visitedPlace) + person2Map.get(visitedPlace);
                    shortestDest = visitedPlace;
                }
            }
        }
        System.out.println("SHORTEST DESTINATON: " + shortestDest + " TIME: " + shortestTime);
    }
    
    public Map<String,Integer> getAllDestinationsWithTime(String person) {
        init();
        Stack visitedPlaces = new Stack();
        Map<String,Integer> placeAndTime = new HashMap<>();
        int time = 0;
        unvisitedPlaces.add(ppl.getOriginGivenPerson(person));
        while (!unvisitedPlaces.isEmpty()) {
            String currentPlace = unvisitedPlaces.pop();
            if (!visitedPlaces.contains(currentPlace)) {
                if (!visitedPlaces.isEmpty() && (!visitedPlaces.peek().equals(currentPlace)) 
                        && c.isLegalConnection((String) visitedPlaces.peek(),currentPlace)) { //HACKYYYYYY
                    time += c.getTimeGivenOriginAndDestination((String) visitedPlaces.peek(), currentPlace);
                }
                visitedPlaces.add(currentPlace);      
                unvisitedPlaces.addAll(c.getDestinations(currentPlace));
                System.out.println(person + " Destination: " + visitedPlaces.peek() + " (" + time + ")");
                placeAndTime.put((String) visitedPlaces.peek(),time);
            }
        }
        System.out.println(person + "'s Visited path: " + visitedPlaces + "Total time: " + time);
        return placeAndTime;
    }

    public void checkIfTwoPeopleCanMeetUsingDFS(String originPerson, String destinationPerson) {
        init();
        Queue visitedPlaces = new LinkedList();
        String destination = null;
        boolean reachedEndPoint = false;
        unvisitedPlaces.add(ppl.getOriginGivenPerson(originPerson));
        while(!reachedEndPoint && !unvisitedPlaces.empty()) {
            String currentPlace = unvisitedPlaces.pop();
            if (!visitedPlaces.contains(currentPlace)) {
                visitedPlaces.add(currentPlace);
                unvisitedPlaces.addAll(c.getDestinations(currentPlace));
                destination = currentPlace;
            }
            if (destination.equals(ppl.getOriginGivenPerson(destinationPerson))) {
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
        int currentTime = 0;
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
}
