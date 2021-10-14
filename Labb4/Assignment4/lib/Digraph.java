import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import java.util.NoSuchElementException;

/**
 * This class represents a digraph based on princetons Digraph.java
 * 
 * @author Malcolm Liljedahl
 * Date: 2021-10-09
 */
public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int VERTICIES;          
    private int edges;             
    private Bag<Integer>[] adj;    // adj[v] = adjacency list for vertex v
    private int[] indegree;        // indegree[v] = indegree of vertex v

    /**
     * Initializes an empty digraph with vertices.
     *
     * @param  VERTICIES the number of vertices
     * @throws IllegalArgumentException if VERTICIES < 0
     */
    @SuppressWarnings("unchecked")
    public Digraph(int VERTICIES) {
        if (VERTICIES < 0) { throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative"); }
        this.VERTICIES = VERTICIES;
        this.edges = 0;
        indegree = new int[VERTICIES];
        adj = (Bag<Integer>[]) new Bag[VERTICIES];
        for (int v = 0; v < VERTICIES; v++) { adj[v] = new Bag<Integer>(); }
    }

    /**  
     * Initializes a digraph from the specified input stream.
     * The format is the number of vertices followed by the number of edges followed by pairs 
     * of vertices, with each entry separated by blankspace.
     *
     * @param  in the input stream
     * @throws IllegalArgumentException if in is null
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     * @throws IllegalArgumentException if the input stream is in the wrong format
     */
    @SuppressWarnings("unchecked")
    public Digraph(In in) {
        if (in == null) { throw new IllegalArgumentException("empty file"); }
        try {
            this.VERTICIES = in.readInt();
            if (VERTICIES < 0) throw new IllegalArgumentException("number of vertices in a Digraph must be non-negative");
            indegree = new int[VERTICIES];
            adj = (Bag<Integer>[]) new Bag[VERTICIES];
            for (int v = 0; v < VERTICIES; v++) { adj[v] = new Bag<Integer>(); }
            int edges = in.readInt();
            if (edges < 0) throw new IllegalArgumentException("number of edges in a Digraph must be non-negative");
            for (int i = 0; i < edges; i++) {
                int v = in.readInt();
                int w = in.readInt();
                addEdge(v, w); 
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
        }
    }

    /**
     * Initializes a new digraph that is a deep copy of the specified digraph.
     *
     * @param  graph the digraph to copy
     * @throws IllegalArgumentException if graph is null
     */
    @SuppressWarnings("unchecked")
    public Digraph(Digraph graph) {
        if (graph == null) throw new IllegalArgumentException("argument is null");
        this.VERTICIES = graph.V();
        this.edges = graph.E();
        if (VERTICIES < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");

        // update indegrees
        indegree = new int[VERTICIES];
        for (int v = 0; v < VERTICIES; v++)
            this.indegree[v] = graph.indegree(v);

        // update adjacency lists
        adj = (Bag<Integer>[]) new Bag[VERTICIES];
        for (int v = 0; v < VERTICIES; v++) {
            adj[v] = new Bag<Integer>();
        }

        for (int v = 0; v < graph.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : graph.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }
        
    /**
     * Returns the number of vertices in this digraph.
     *
     * @return the number of vertices in this digraph
     */
    public int V() { return VERTICIES; }

    /**
     * Returns the number of edges in this digraph.
     *
     * @return the number of edges in this digraph
     */
    public int E() { return edges; }


    // throw an IllegalArgumentException unless {@code 0 <= v < VERTICIES}
    private void validateVertex(int v) {
        if (v < 0 || v >= VERTICIES) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (VERTICIES-1));
        }
    }

    /**
     * Adds the directed edge v→w to this digraph.
     *
     * @param  tailVertex the tail vertex
     * @param  headVertex the head vertex
     * @throws IllegalArgumentException unless both  0 <= tailVertex < VERTICIES 
     * and 0 <= headVertex < VERTICIES
     */
    public void addEdge(int tailVertex, int headVertex) {
        validateVertex(tailVertex);
        validateVertex(headVertex);
        adj[tailVertex].add(headVertex);
        indegree[headVertex]++;
        edges++;
    }

    /**
     * Returns the vertices adjacent from vertex in this digraph.
     *
     * @param  vertex the vertex
     * @return the vertices adjacent from vertex in this digraph, as an iterable
     * @throws IllegalArgumentException unless  0 <= vertex < VERTICIES
     */
    public Iterable<Integer> adj(int vertex) {
        validateVertex(vertex);
        return adj[vertex];
    }

    /**
     * Returns the number of directed edges incident from vertex.
     *
     * @param  vertex the vertex
     * @return the outdegree of vertex              
     * @throws IllegalArgumentException unless 0 <= vertex < VERTICIES
     */
    public int outdegree(int vertex) {
        validateVertex(vertex);
        return adj[vertex].size();
    }

    /**
     * Returns the number of directed edges incident to vertex.
     *
     * @param  vertex the vertex
     * @return the indegree of vertex             
     * @throws IllegalArgumentException unless 0 <= vertex < VERTICIES
     */
    public int indegree(int vertex) {
        validateVertex(vertex);
        return indegree[vertex];
    }

    /**
     * Returns the reverse of the digraph.
     *
     * @return the reverse of the digraph
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(VERTICIES);
        for (int v = 0; v < VERTICIES; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return the number of vertices, followed by the number of edges, followed by the 
     * adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(VERTICIES + " vertices, " + edges + " edges " + NEWLINE);
        for (int v = 0; v < VERTICIES; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
    
}
