package datastructures;

import java.util.*;

public class GraphAdjacencyList {
    private final int V;
    private final List<List<Edge>> adj;
    
    static class Edge {
        int dest;
        int weight;
        
        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
    
    public GraphAdjacencyList(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }
    
    // Add edge
    public void addEdge(int src, int dest, int weight) {
        adj.get(src).add(new Edge(dest, weight));
        // For undirected graph, uncomment the following line:
        // adj.get(dest).add(new Edge(src, weight));
    }
    
    // Remove edge
    public void removeEdge(int src, int dest) {
        adj.get(src).removeIf(edge -> edge.dest == dest);
        // For undirected graph, uncomment the following line:
        // adj.get(dest).removeIf(edge -> edge.dest == src);
    }
    
    // Check if edge exists
    public boolean hasEdge(int src, int dest) {
        return adj.get(src).stream().anyMatch(edge -> edge.dest == dest);
    }
    
    // Get weight of edge
    public Optional<Integer> getWeight(int src, int dest) {
        return adj.get(src).stream()
                .filter(edge -> edge.dest == dest)
                .map(edge -> edge.weight)
                .findFirst();
    }
    
    // Get all neighbors of a vertex
    public List<Integer> getNeighbors(int v) {
        List<Integer> neighbors = new ArrayList<>();
        for (Edge edge : adj.get(v)) {
            neighbors.add(edge.dest);
        }
        return neighbors;
    }
    
    public static void main(String[] args) {
        GraphAdjacencyList g = new GraphAdjacencyList(4);
        
        // Add edges
        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 15);
        g.addEdge(1, 2, 20);
        g.addEdge(2, 3, 25);
        
        // Demonstrate usage
        System.out.println("Edge between 0 and 1: " + g.hasEdge(0, 1));
        System.out.println("Weight between 0 and 1: " + g.getWeight(0, 1).orElse(-1));
        System.out.println("Neighbors of vertex 2: " + g.getNeighbors(2));
        
        // Remove edge and verify
        g.removeEdge(0, 1);
        System.out.println("Edge between 0 and 1 after removal: " + g.hasEdge(0, 1));
    }
}
