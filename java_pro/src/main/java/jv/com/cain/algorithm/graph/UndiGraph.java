package jv.com.cain.algorithm.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class UndiGraph {
    // num of vertex
    private final int vertexes;
    // adj is an array
    private final LinkedList<Integer>[] adj;
    private boolean dfsFound = false;

    public UndiGraph(int vertexes) {
        this.vertexes = vertexes;
        this.adj = new LinkedList[vertexes];
        for (int i = 0; i < vertexes; i++) {
            this.adj[i] = new LinkedList<>();
        }
    }

    /**
     * add an edge to the adj
     *
     * @param source one vertex of edge
     * @param target other vertex of edge
     */
    public void addEdge(int source, int target) {
        adj[source].add(target);
        adj[target].add(source);
    }

    @SuppressWarnings("unused")
    public int getVertexes() {
        return vertexes;
    }

    /**
     * Time complexity: O(Edge)
     * Spatial complexity: O(Vertex)
     *
     * @param source the vertex which start from
     * @param target the target vertex
     */
    public void bfs(int source, int target) {
        if (source == target) return;
        // check vertex is visited
        boolean[] visited = new boolean[vertexes];
        visited[source] = true;

        // every level vertex
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        // save the previous vertex of index vertex
        int[] pre = new int[vertexes];
        Arrays.fill(pre, -1);

        while (!queue.isEmpty()) {
            int topVertex = queue.poll();
            // visit the vertex's all edge
            for (int i = 0; i < adj[topVertex].size(); i++) {
                int edgeVertex = adj[topVertex].get(i);
                if (!visited[edgeVertex]) {
                    pre[edgeVertex] = topVertex;
                    // arrive at target
                    if (edgeVertex == target) {
                        printPath(pre, source, target);
                        return;
                    }
                    visited[edgeVertex] = true;
                    queue.add(edgeVertex);
                }
            }
        }
    }

    /**
     * print the shortest path
     *
     * @param source the vertex which start from
     * @param target the target vertex
     */
    private void printPath(int[] pre, int source, int target) {
        if (pre[target] != -1 && target != source) {
            printPath(pre, source, pre[target]);
        }
        System.out.print(target + ", ");
    }

    /**
     * Time complexity: O(Edge)
     * Spatial complexity: O(Vertex)
     * @param source the vertex which start from
     * @param target the target vertex
     */
    public void dfs(int source, int target) {
        dfsFound = false;
        boolean[] visited = new boolean[vertexes];

        int[] pre = new int[vertexes];
        Arrays.fill(pre, -1);

        doDfsRecursive(source, target, visited, pre);
        printPath(pre, source, target);
    }

    private void doDfsRecursive(int source, int target, boolean[] visited, int[] pre) {
        if (dfsFound) return;
        visited[source] = true;
        // arrive at the target vertex
        if (source == target) {
            dfsFound = true;
            return;
        }
        // visit the vertex's all edge
        for (int i = 0; i < adj[source].size(); i++) {
            int edgVertex = adj[source].get(i);
            if (!visited[edgVertex]) {
                pre[edgVertex] = source;
                doDfsRecursive(edgVertex, target, visited, pre);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("==========test BFS and DFS==========");
        testXFS();
    }

    public static void testXFS() {
        UndiGraph graph = new UndiGraph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        System.out.print("bfs path: ");
        graph.bfs(0, 7);

        System.out.println();

        System.out.print("dfs path: ");
        graph.dfs(0,7);
    }
}
