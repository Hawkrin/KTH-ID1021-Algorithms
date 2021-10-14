import edu.princeton.cs.algs4.Stack;

/**
 * A depth-first-search class based on princetons BreadthFirstSearch.java
 * 
 * @author Malcolm Liljedahl
 * Date: 2021-10-09
 */
public class A4DepthFirstPath {
    private boolean[] marked;  // marked[v] = true iff v is reachable from s
    private int[] edgeTo;      // edgeTo[v] = last edge on path from s to v
    private final int sourceVertex;       // source vertex
   

    /**
     * Computes a directed path from sourceVertex to every other vertex in digraph.
     * @param  graph the digraph
     * @param  sourceVertex the source vertex
     * @throws IllegalArgumentException unless 0 <= s < V
     */
    public A4DepthFirstPath(Digraph graph, int sourceVertex) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.sourceVertex = sourceVertex;
        validateVertex(sourceVertex);
        dfs(graph, sourceVertex);
    }
    private void dfs(Digraph graph, int vertex) { 
        marked[vertex] = true;
        for (int w : graph.adj(vertex)) {
            if (!marked[w]) {
                edgeTo[w] = vertex;
                dfs(graph, w);
            }
        }
    }

    /**
     * Checks if there is a directed path from the source vertex  to a vertex
     * @param  vertex the vertex
     * @return true if there's a path, otherwise false
     * @throws IllegalArgumentException unless 0 <= v < V
     */
    public boolean hasPathTo(int vertex) {
        validateVertex(vertex);
        return marked[vertex];
    }

    /**
     * Returns a directed path from the source vertex to a vertex or returns null if no such 
     * path exist.
     * @param  vertex the vertex
     * @return the sequence of vertices on a directed path from the source vertex
     *         to another vertex as an Iterable
     * @throws IllegalArgumentException unless 0 <= v < V
     */
    public Iterable<Integer> pathTo(int vertex) {
        validateVertex(vertex);
        if (!hasPathTo(vertex)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = vertex; x != sourceVertex; x = edgeTo[x]) { path.push(x); }
        path.push(sourceVertex);
        return path;
    }

    private void validateVertex(int vertex) {
        int V = marked.length;
        if (vertex < 0 || vertex >= V)
            throw new IllegalArgumentException("vertex " + vertex + " is not between 0 and " + (V-1));
    }



    
}
