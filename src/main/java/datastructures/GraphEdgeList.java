package datastructures;

import java.util.*;

public class GraphEdgeList {
    private final int V;
    private final List<Edge> edges;
    
    static class Edge {
        int src;
        int dest;
        int weight;
        
        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
        
        @Override
        public String toString() {
            return String.format("(%d -> %d, weight: %d)", src, dest, weight);
        }
    }
    
    public GraphEdgeList(int V) {
        this.V = V;
        this.edges = new ArrayList<>();
    }
    
    // Add edge
    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
        // For undirected graph, uncomment the following line:
        // edges.add(new Edge(dest, src, weight));
    }
    
    // Remove edge
    public void removeEdge(int src, int dest) {
        edges.removeIf(edge -> edge.src == src && edge.dest == dest);
        // For undirected graph, uncomment the following line:
        // edges.removeIf(edge -> edge.src == dest && edge.dest == src);
    }
    
    // Check if edge exists
    public boolean hasEdge(int src, int dest) {
        return edges.stream().anyMatch(e -> e.src == src && e.dest == dest);
    }
    
    // Get weight of edge
    public Optional<Integer> getWeight(int src, int dest) {
        return edges.stream()
                .filter(e -> e.src == src && e.dest == dest)
                .map(e -> e.weight)
                .findFirst();
    }
    
    // Get all neighbors of a vertex
    public List<Integer> getNeighbors(int v) {
        List<Integer> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.src == v) {
                neighbors.add(edge.dest);
            }
        }
        return neighbors;
    }
    
    // Get all edges
    public List<Edge> getAllEdges() {
        return new ArrayList<>(edges);
    }
    
    public static void main(String[] args) {
        GraphEdgeList g = new GraphEdgeList(4);
        
        // Add edges
        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 15);
        g.addEdge(1, 2, 20);
        g.addEdge(2, 3, 25);
        
        // Demonstrate usage
        System.out.println("All edges: " + g.getAllEdges());
        System.out.println("Edge between 0 and 1: " + g.hasEdge(0, 1));
        System.out.println("Weight between 0 and 1: " + g.getWeight(0, 1).orElse(-1));
        System.out.println("Neighbors of vertex 2: " + g.getNeighbors(2));
        
        // Remove edge and verify
        g.removeEdge(0, 1);
        System.out.println("Edge between 0 and 1 after removal: " + g.hasEdge(0, 1));
    }
}
