import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;

/**
 * A breadth-first-search class based on princetons BreadthFirstSearch.java
 * 
 * @author Malcolm Liljedahl
 * Date: 2021-10-09
 */
public class A3BreadthFirstPath {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] from;
    private int[] distTo;

    /**
     * Computes the shortest path between THE source vertex and every other vertex in the graph
     * 
     * @param graph the graph
     * @param sourceVertex the source vertex
     * @throws IllegalArgumentException unless 0 <= sourceVertex < V
     */
    public A3BreadthFirstPath(Graph graph, int sourceVertex) {
        marked = new boolean[graph.V()];
        from = new int[graph.V()];
        distTo = new int[graph.V()];
        validateVertex(sourceVertex);
        bfs(graph, sourceVertex);
    }

    /**
     * Computes the shortest path between ANY source verticies to every other vertex in the graph
     * 
     * @param graph the graph
     * @param sourceVertex a source vertex
     * @throws IllegalArgumentException unless 0 <= sourceVertex < V
     * @throws IllegalArgumentException sourceVertex is null
     */
    public A3BreadthFirstPath(Graph graph, Iterable<Integer> sourceVertex) {
        marked = new boolean[graph.V()];
        from = new int[graph.V()];
        distTo = new int[graph.V()];
        for(int i = 0; i < graph.V(); i++) { distTo[i] = INFINITY; }
        validateVertices(sourceVertex);
        bfs(graph, sourceVertex);
    }

    //Breadthfirst search from THE source
    private void bfs(Graph graph, int sourceVertex) {
        DoubleLinkedCircularList<Integer> queue = new DoubleLinkedCircularList<Integer>();
        for(int i = 0; i < graph.V(); i++) { distTo[i] = INFINITY; }
        distTo[sourceVertex] = 0;
        marked[sourceVertex] = true;
        queue.enqueue(sourceVertex);
        while(!queue.isEmpty()) {
            int vertex = queue.dequeue();
            for(int w : graph.adj(vertex)) {
                if(!marked[w]) {
                    from[w] = vertex;
                    distTo[w] = distTo[vertex] + 1;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    //Breadthfirst search from ANY source
    private void bfs(Graph graph, Iterable<Integer> sourceVerticies) {
        DoubleLinkedCircularList<Integer> queue = new DoubleLinkedCircularList<Integer>();
        for(int source : sourceVerticies) {
            marked[source] = true;
            distTo[source] = 0;
            queue.enqueue(source);
        }
        while(!queue.isEmpty()) {
            int vertex = queue.dequeue();
            for(int w : graph.adj(vertex)) {
                if(!marked[w]) {
                    from[w] = vertex;
                    distTo[w] = distTo[vertex] + 1;
                    marked[w] = true;
                    queue.enqueue(w);

                }
            }
        }
    }

    /**
     * Checks if there's a path between the source(s) and the vertex
     * 
     * @param vertex the vertex
     * @return true if a path exists, otherwise false.
     * @throws IllegalArgumentException unless 0 <= vertex < V
     */
    public boolean hasPathTo(int vertex) {
        validateVertex(vertex);
        return marked[vertex];
    }

    /**
     * Returns the number of edges in the shortest path between the source(s) vertex and 
     * the vertex
     * 
     * @param vertex the vertex
     * @return the number of edges in the shortest path
     * @throws IllegalArgumentException unless 0 <= vertex < V
     */
    public int distTo(int vertex) {
        validateVertex(vertex);
        return distTo[vertex];
    }

    /**
     * Returns the shortest path between the source(s) vertex and the vertex.
     * Returns null if no such path exist
     * 
     * @param vertex the vertex
     * @return the verticies in the shortest path
     * @throws IllegalArgumentException unless 0 <= vertex < V
     */
    public Iterable<Integer> pathTo(int vertex) {
        validateVertex(vertex);
        if(!hasPathTo(vertex)) { return null; }
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for(x = vertex; distTo[x] != 0; x = from[x]) { path.push(x); }
        path.push(x);
        return path;
    }

    private void validateVertex(int vertex) {
        int V = marked.length;
        if(vertex < 0 ||vertex >= V) {
            throw new IllegalArgumentException("vertex " + vertex + " is not between 0 and " + (V-1));
        }
    }

    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) { throw new IllegalArgumentException("argument is null"); }
        int count = 0;
        for (Integer v : vertices) {
            count++;
            if (v == null) { throw new IllegalArgumentException("vertex is null"); }
            validateVertex(v);
        }
        if (count == 0) { throw new IllegalArgumentException("zero vertices"); }
    }


    
}
