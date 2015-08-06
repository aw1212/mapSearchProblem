package com.alessandra.search;


public class Initializer {
    
    private static Connections c = new Connections();
    private static People ppl = new People();
    
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
    
}
