import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Add vertices for all of the labeled buildings
        String[] vertices = {
                "Liberal Arts", // 0
                "Student Services", // 1
                "Health Careers & Sciences", // 2
                "Health Technologies Center", // 3
                "Recreation Center", // 4
                "Technology Learning Center", // 5
                "Business & Technology", // 6
                "Theatre" // 7
        };

        // 2. Add edges for connected areas (walking paths)
        // Based on the campus layout where buildings form a circuit/hub
        int[][] edges = {
                { 0, 1 }, { 0, 7 }, // Liberal Arts
                { 1, 0 }, { 1, 2 }, { 1, 7 }, // Student Services
                { 2, 1 }, { 2, 3 }, { 2, 5 }, // Health Careers & Sciences
                { 3, 2 }, { 3, 4 }, // Health Technologies Center
                { 4, 3 }, { 4, 5 }, // Recreation Center
                { 5, 2 }, { 5, 4 }, { 5, 6 }, // Technology Learning Center
                { 6, 5 }, { 6, 7 }, // Business & Technology
                { 7, 0 }, { 7, 1 }, { 7, 6 } // Theatre
        };

        // 3. Create the graph using vertices and edges
        UnweightedGraph<String> graph = new UnweightedGraph<>(vertices, edges);

        // 4. Perform DFS starting from "Business & Technology" (Vertex 6)
        UnweightedGraph<String>.SearchTree dfs = graph.dfs(6);

        // 5. Retrieve and print the search order
        List<Integer> searchOrder = dfs.getSearchOrder();
        System.out.println("DFS Search Order from Business & Technology:");
        for (int i = 0; i < searchOrder.size(); i++) {
            System.out.print(vertices[searchOrder.get(i)] + (i < searchOrder.size() - 1 ? " -> " : ""));
        }
        System.out.println("\n");

        // 6. Print the parent-child relationships
        System.out.println("DFS Parent-Child Relationships:");
        for (int i = 0; i < vertices.length; i++) {
            if (dfs.getParent(i) != -1) {
                System.out.println(vertices[dfs.getParent(i)] + " is the parent of " + vertices[i]);
            } else {
                System.out.println(vertices[i] + " is the root (no parent)");
            }
        }
        System.out.println();

        // 7. Call printPath() for specific buildings
        System.out.println("Paths from Business & Technology:");

        System.out.print("To Health Technologies Center: ");
        dfs.printPath(3);

        System.out.print("\nTo Student Services: ");
        dfs.printPath(1);

        System.out.print("\nTo Recreation Center: ");
        dfs.printPath(4);
        System.out.println("\n");

        // 8. Call printTree() to print the entire tree
        System.out.println("The entire DFS Tree:");
        dfs.printTree();
    }
}