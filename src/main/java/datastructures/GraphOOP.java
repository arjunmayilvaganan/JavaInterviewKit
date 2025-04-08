package datastructures;

import java.util.*;
import java.util.stream.Collectors;

public class GraphOOP {
    private final Map<Vertex, List<Edge>> adjacencyMap;
    
    static class Vertex {
        private final String label;
        private Map<String, Object> properties;
        
        public Vertex(String label) {
            this.label = label;
            this.properties = new HashMap<>();
        }
        
        public void setProperty(String key, Object value) {
            properties.put(key, value);
        }
        
        public Object getProperty(String key) {
            return properties.get(key);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return Objects.equals(label, vertex.label);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(label);
        }
        
        @Override
        public String toString() {
            return "Vertex(" + label + ")";
        }
    }
    
    static class Edge {
        private final Vertex source;
        private final Vertex destination;
        private final String type;
        private Map<String, Object> properties;
        
        public Edge(Vertex source, Vertex destination, String type) {
            this.source = source;
            this.destination = destination;
            this.type = type;
            this.properties = new HashMap<>();
        }
        
        public void setProperty(String key, Object value) {
            properties.put(key, value);
        }
        
        public Object getProperty(String key) {
            return properties.get(key);
        }
        
        @Override
        public String toString() {
            return String.format("Edge(%s -[%s]-> %s)", source, type, destination);
        }
    }
    
    public GraphOOP() {
        this.adjacencyMap = new HashMap<>();
    }
    
    public Vertex addVertex(String label) {
        Vertex vertex = new Vertex(label);
        adjacencyMap.putIfAbsent(vertex, new ArrayList<>());
        return vertex;
    }
    
    public Edge addEdge(Vertex source, Vertex destination, String type) {
        if (!adjacencyMap.containsKey(source) || !adjacencyMap.containsKey(destination)) {
            throw new IllegalArgumentException("Both vertices must exist in the graph");
        }
        
        Edge edge = new Edge(source, destination, type);
        adjacencyMap.get(source).add(edge);
        return edge;
    }
    
    public List<Edge> getEdges(Vertex source) {
        return adjacencyMap.getOrDefault(source, Collections.emptyList());
    }
    
    public List<Vertex> getNeighbors(Vertex source) {
        return getEdges(source).stream()
                .map(edge -> edge.destination)
                .collect(Collectors.toList());
    }
    
    public static void main(String[] args) {
        GraphOOP graph = new GraphOOP();
        
        // Create vertices
        Vertex v1 = graph.addVertex("A");
        Vertex v2 = graph.addVertex("B");
        Vertex v3 = graph.addVertex("C");
        
        // Add properties to vertices
        v1.setProperty("color", "red");
        v2.setProperty("color", "blue");
        
        // Create edges
        Edge e1 = graph.addEdge(v1, v2, "CONNECTS_TO");
        Edge e2 = graph.addEdge(v2, v3, "CONNECTS_TO");
        
        // Add properties to edges
        e1.setProperty("weight", 10);
        e2.setProperty("weight", 20);
        
        // Demonstrate usage
        System.out.println("Edges from A: " + graph.getEdges(v1));
        System.out.println("Neighbors of A: " + graph.getNeighbors(v1));
        System.out.println("Color of A: " + v1.getProperty("color"));
        System.out.println("Weight of edge A->B: " + e1.getProperty("weight"));
    }
}
