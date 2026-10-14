package Graphs;

public class Main {
    public static void main(String[] args) {
        Graph myGraph = new Graph();

        // Add Vertex
        myGraph.addVertex("A");
        myGraph.addVertex("B");
        myGraph.addVertex("C");
        myGraph.addVertex("D");

        // Add Edge
        myGraph.addEdge("A", "B");
        myGraph.addEdge("A", "C");
        myGraph.addEdge("A", "D");
        myGraph.addEdge("B", "D");
        myGraph.addEdge("C", "D");

        // Remove Edges
//        myGraph.removeEdges("A", "B");

        // Remove Vertex
        myGraph.removeVertex("D");

        myGraph.printGraph();
    }
}
