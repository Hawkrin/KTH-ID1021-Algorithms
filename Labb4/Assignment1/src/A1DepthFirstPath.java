import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Graph;

/**
 * A depth-first-search class based on princetons DepthFirstSearch.java
 * 
 * @author Malcolm Liljedahl
 * Date: 2021-10-09
 */
public class A1DepthFirstPath {
    private boolean[] marked;   
    private int[] from;       
    private final int sourceVertex;         

    /**
     * Computes a path between {@code s} and every other vertex in graph {@code G}.
     * @param graph the graph
     * @param sourceVertex the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public A1DepthFirstPath(Graph graph, int sourceVertex) {
        this.sourceVertex = sourceVertex;
        from = new int[graph.V()];
        marked = new boolean[graph.V()];
        validateVertex(sourceVertex);
        dfs(graph, sourceVertex);
    }

    // depth first search from v
    private void dfs(Graph graph, int vertex) {
        marked[vertex] = true;
        for (int w : graph.adj(vertex)) {
            if (!marked[w]) {
                from[w] = vertex;
                dfs(graph, w);
            }
        }
    }

    /**
     * Checks if there is a path between the source vertex
     * @param vertex the vertex
     * @return {@code true} if there is a path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int vertex) {
        validateVertex(vertex);
        return marked[vertex];
    }

    /**
     * Returns a path between the source vertex and vertex, or null if no such path exists.
     * @param  vertex the vertex
     * @return the sequence of vertices on a path between the source vertex and the searched vertex 
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> pathTo(int vertex) {
        validateVertex(vertex);
        if (!hasPathTo(vertex)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = vertex; x != sourceVertex; x = from[x]) {
            path.push(x);
        }
        path.push(sourceVertex);
        return path;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int vertex) {
        int V = marked.length;
        if (vertex < 0 || vertex >= V) {
            throw new IllegalArgumentException("vertex " + vertex + " is not between 0 and " + (V-1));
        }
    }


    
}
