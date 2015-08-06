package com.alessandra.search;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import static com.alessandra.search.Initializer.*;

public class Search {
    
    private static final int MAX_DEPTH = 3;
    private static final int MAX_TIME = 5;
    private static int currentTime = 0;
    private static Stack<String> unvisitedPlaces = new Stack<>();
    private static Connections c = new Connections();
    private static People ppl = new People();
    
    public static void dfs(String originPerson, String destinationPerson) {
        init();
        Queue visitedPlaces = new LinkedList();
        String destination = null;
        boolean reachedEndPoint = false;
        unvisitedPlaces.add(ppl.getOriginGivenPerson(originPerson));
        while(!reachedEndPoint && !unvisitedPlaces.empty()) {
            System.out.println("Unvisited places: " + unvisitedPlaces);
            System.out.println("Visited places: " + visitedPlaces);
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
    
    //omg this method is so hacky don't judge me!
    public static void getShortestPathForPeople(String person1, String person2) {
        Map<String,Integer> person1Map = getAllDestinationsWithTime(person1);
        System.out.println("Person1 map: "+person1Map);
        Map<String,Integer> person2Map = getAllDestinationsWithTime(person2);
        System.out.println("Person2 map: "+person2Map);
        
        int shortestTime = 10000; //change this it's so hacky!
        int num1;
        int num2;
        String shortestDest = null;
        for (int i = 0; i < Math.min(person1Map.size(),person2Map.size()); i++) {
            for (HashMap.Entry<String,Integer> entry1 : person1Map.entrySet()) {
                for (HashMap.Entry<String,Integer> entry2 : person2Map.entrySet()) {
                    if (entry1.getKey().equals(entry2.getKey())) {
                        num1 = (entry1.getValue());
                        num2 = (entry2.getValue());
                        if (num1+num2 < shortestTime) {
                            shortestTime = num1+num2;
                            shortestDest = (entry1.getKey());
                        } //omg look at all these curly brackets ahhhhhh!
                    }
                }
            }
        }
        System.out.println("SHORTEST DESTINATON: " + shortestDest + " TIME: " + shortestTime);
    }
    
    public static Map<String,Integer> getAllDestinationsWithTime(String person) {
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
                System.out.println("Destination: " + visitedPlaces.peek() + " Time: " + time);
                placeAndTime.put((String) visitedPlaces.peek(),time);
            }
        }
        System.out.println("Visited path: " + visitedPlaces + "Total time: " + time);
        return placeAndTime;
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
         
}
