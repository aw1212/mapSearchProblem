package com.alessandra.search;

public class Main {

    public static void main(String[] args) {
        Search search = new Search();
        //getShortestCompletePathGivenPersonUnderCertainTime("Zim");
        //getShortestCompletePathGivenPersonUnderCertainDepth("Zim");
        //search.checkIfTwoPeopleCanMeetUsingDFS("Gir", "Zim");
        //search.getAllDestinationsWithTime("Zim");
        //getAllDestinationsWithTime("Gir");
        search.getShortestPathForPeople("Zim", "Gir");
    }

}
