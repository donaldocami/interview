package com.challenge.application.weightedpath;

public class GraphMapper {

    public static Node mapStringToNode(String nodeName){
        return new Node(nodeName);

    }

    //String[] graphArray = new String[]{"4", "A", "B", "C", "D", "A|B|2", "C|B|11", "C|D|3", "B|D|2"};
    public static Graph routeSetUp(Graph graph, String route){
        String[] routeArray = route.split("\\|");
        Node startNode = graph.getNodeByName(routeArray[0]);
        Node destinationNode = graph.getNodeByName(routeArray[1]);
        int weight = Integer.parseInt(routeArray[2]);
        startNode.getAdjacentNodes().put(destinationNode,weight);
        return graph;
    }


}
