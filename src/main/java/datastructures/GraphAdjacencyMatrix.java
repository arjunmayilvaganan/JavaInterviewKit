package datastructures;

import java.util.*;

public class GraphAdjacencyMatrix {
    private final int V; // number of vertices
    private final int[][] adj; // adjacency matrix
    
    public GraphAdjacencyMatrix(int V) {
        this.V = V;
        adj = new int[V][V];
    }
    
    // Add edge between vertices
    public void addEdge(int v, int w, int weight) {
        adj[v][w] = weight;
        // For undirected graph, uncomment the following line:
        // adj[w][v] = weight;
    }
    
    // Remove edge between vertices
    public void removeEdge(int v, int w) {
        adj[v][w] = 0;
        // For undirected graph, uncomment the following line:
        // adj[w][v] = 0;
    }
    
    // Check if edge exists
    public boolean hasEdge(int v, int w) {
        return adj[v][w] != 0;
    }
    
    // Get weight of edge
    public int getWeight(int v, int w) {
        return adj[v][w];
    }
    
    // Get all neighbors of a vertex
    public List<Integer> getNeighbors(int v) {
        List<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[v][i] != 0) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }
    
    public static void main(String[] args) {
        GraphAdjacencyMatrix g = new GraphAdjacencyMatrix(4);
        
        // Add edges
        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 1);
        
        // Demonstrate usage
        System.out.println("Edge between 0 and 1: " + g.hasEdge(0, 1));
        System.out.println("Edge between 0 and 3: " + g.hasEdge(0, 3));
        System.out.println("Neighbors of vertex 2: " + g.getNeighbors(2));
        
        // Remove edge and verify
        g.removeEdge(0, 1);
        System.out.println("Edge between 0 and 1 after removal: " + g.hasEdge(0, 1));
    }
}
