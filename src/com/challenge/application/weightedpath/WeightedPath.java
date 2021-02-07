package com.challenge.application.weightedpath;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WeightedPath {
    private static final String unreachableNode = "-1";

    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacencyPair :
                    currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(Node evaluationNode,
                                                 Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    public static String getShortestPathToDestination(Graph graph, String sourceNodeString, String destinationNodeString) {
        graph = calculateShortestPathFromSource(graph, graph.getNodeByName(sourceNodeString));
        Node destinationNode = graph.getNodeByName(destinationNodeString);
        List<Node> shortestPathToDestination = destinationNode.getShortestPath();
        if (shortestPathToDestination.size() == 0)
            return unreachableNode;
        String path = shortestPathToDestination.stream().map(n -> n.getName())
                .collect(Collectors.joining("-"));
        path = path + "-" + destinationNodeString;
        return path;

    }

    public static void main(String args[]) {
        String[] graphArray = new String[]{"4", "A", "B", "C", "D", "A|B|2", "C|B|11", "C|D|3", "B|D|2"};

        int nrOfNodes = Integer.valueOf(graphArray[0]);
        final String sourceNodeName = graphArray[1];
        final String destinationNodeName = graphArray[nrOfNodes];
        Graph graph = new Graph();
        setUp(graph, graphArray);
        System.out.println("got path : " + getShortestPathToDestination(graph, sourceNodeName, destinationNodeName));

    }

    public static void setUp(Graph graph, String[] graphArray) {

        int nrOfNodesIndex = Integer.parseInt(graphArray[0]);
        Set<Node> nodeList = IntStream.rangeClosed(1, nrOfNodesIndex).mapToObj(i -> new Node(graphArray[i]))
                                                                     .collect(Collectors.toSet());

        graph.setNodes(nodeList);
        IntStream.rangeClosed(nrOfNodesIndex + 1, graphArray.length - 1).forEach(i -> GraphMapper.routeSetUp(graph, graphArray[i]));

    }


}
