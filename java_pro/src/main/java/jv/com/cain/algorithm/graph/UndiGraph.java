package jv.com.cain.algorithm.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unused")
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
     *
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
        graph.dfs(0, 7);
    }

    /**
     * refe: https://leetcode.cn/problems/number-of-islands/solution/dao-yu-shu-liang-by-leetcode/ method1
     * Time complexity: O(rc)
     * Spatial complexity: O(rc)
     *
     * @param grid a two-dimensional array that represents grid
     * @return the number of island
     */
    public static int queryIslandsNum(char[][] grid) {
        int islandNum = 0;
        if (grid == null || grid.length == 0) {
            return islandNum;
        }

        int row = grid.length;
        int column = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1') {
                    // every get in to this if condition, islandNum++
                    islandNum++;
                    doDfs(grid, i, j);
                }
            }
        }
        return islandNum;
    }

    private static void doDfs(char[][] grid, int r, int c) {
        int row = grid.length;
        int column = grid[0].length;
        // out of border or it has been visited
        if (r < 0 || r >= row || c < 0 || c >= column || grid[r][c] == '0') {
            return;
        }

        // mark as visited
        grid[r][c] = '0';
        doDfs(grid, r - 1, c);// top
        doDfs(grid, r + 1, c);// bottom
        doDfs(grid, r, c - 1);// left
        doDfs(grid, r, c + 1);// right
    }

    /**
     * refe: https://leetcode.cn/problems/valid-sudoku/solution/you-xiao-de-shu-du-by-leetcode-solution-50m6/ method1
     * Time complexity: O(1)
     * Spatial complexity: O(1)
     *
     * @param board a two-dimensional array
     * @return return if it is sudoku or false
     */
    public static boolean isValidSudoku(char[][] board) {
        // check row
        int[][] rows = new int[9][9];
        // check column
        int[][] columns = new int[9][9];
        // check 3 X 3 sub board
        int[][][] subBoxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    rows[i][index]++;
                    columns[j][index]++;
                    subBoxes[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1 || subBoxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void testQueryIslandNum() {
        char[][] grid = new char[4][3];
        grid[0][0] = '1';
        grid[0][1] = '1';
        grid[0][2] = '1';
        grid[1][0] = '0';
        grid[1][1] = '1';
        grid[1][2] = '0';
        grid[2][0] = '1';
        grid[2][1] = '0';
        grid[2][2] = '0';
        grid[3][0] = '1';
        grid[3][1] = '0';
        grid[3][2] = '1';
        System.out.println("number of island: " + queryIslandsNum(grid));
    }

    public static void main(String[] args) {
        System.out.println("==========test BFS and DFS==========");
        testXFS();

        System.out.println();
        System.out.println("==========test Query Island Num==========");
        testQueryIslandNum();
    }
}
