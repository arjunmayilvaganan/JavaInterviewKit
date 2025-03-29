package datastructures;

public class DisjointSet {
    private int[] parent;
    private int[] rank;    // rank[i] represents the size of the set containing i
    
    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;   // each element is its own parent initially
            rank[i] = 1;     // each set has size 1 initially
        }
    }
    
    public int find(int node) {
        int cur = node;
        while (cur != parent[cur]) {
            parent[cur] = parent[parent[cur]];  // path compression
            cur = parent[cur];
        }
        return cur;
    }
    
    public boolean union(int u, int v) {
        int pu = find(u);
        int pv = find(v);
        
        if (pu == pv) {
            return false;  // already in same set
        }
        
        // Make sure pu points to the root of the larger set
        if (rank[pv] > rank[pu]) {
            int temp = pu;
            pu = pv;
            pv = temp;
        }
        
        // Attach smaller set to larger set
        parent[pv] = pu;
        rank[pu] += rank[pv];  // update size of the merged set
        return true;
    }
    
    // Helper method to get size of the set containing node
    public int getSetSize(int node) {
        return rank[find(node)];
    }
    
    // Helper method to check if two nodes are connected
    public boolean isConnected(int u, int v) {
        return find(u) == find(v);
    }
    
    public static void main(String[] args) {
        // Example usage with the graph problem
        int n = 5;
        int[][] edges = {{0,1}, {1,2}, {3,4}};
        
        DisjointSet ds = new DisjointSet(n);
        int components = n;  // Start with n components
        
        System.out.println("Initial number of components: " + components);
        
        for (int[] edge : edges) {
            if (ds.union(edge[0], edge[1])) {
                components--;
                System.out.println("Connected " + edge[0] + " - " + edge[1] + 
                                 ", components: " + components);
            }
        }
        
        System.out.println("Final number of components: " + components);
        
        // Demonstrate set sizes
        System.out.println("Size of set containing 0: " + ds.getSetSize(0));  // Should be 3
        System.out.println("Size of set containing 3: " + ds.getSetSize(3));  // Should be 2
        
        // Demonstrate connectivity
        System.out.println("Is 0-2 connected? " + ds.isConnected(0, 2));  // Should be true
        System.out.println("Is 0-3 connected? " + ds.isConnected(0, 3));  // Should be false
    }
}