import edu.princeton.cs.algs4.Bag;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

/**
 * A class that creates a graph based on princetons Graph.java
 * 
 * @author Malcolm Liljedahl
 * Date: 2021-10-09
 */
public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int verticies;
    private int edges;
    private Bag<Integer>[] adj;
    
    /**
     * Initializes an empty graph with vertices and 0 edges.
     * param verticies the number of vertices
     *
     * @param  verticies number of vertices
     * @throws IllegalArgumentException if  verticies < 0
     */
    @SuppressWarnings("unchecked")
    public Graph(int verticies) {
        if (verticies < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.verticies = verticies;
        this.edges = 0;
        adj = (Bag<Integer>[]) new Bag[verticies];
        for (int v = 0; v < verticies; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**  
     * Initializes a graph from the specified input stream.
     * The format is the number of vertices followed by the number of edges, followed by 
     * pairs of vertices, with each entry separated by whitespace.
     *
     * @param  in the input stream
     * @throws IllegalArgumentException if input is null
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     * @throws IllegalArgumentException if the input stream is in the wrong format
     */
    @SuppressWarnings("unchecked")
    public Graph(In in) {
        if (in == null) throw new IllegalArgumentException("argument is null");
        try {
            this.verticies = in.readInt();
            if (verticies < 0) throw new IllegalArgumentException("number of vertices in a graph must be non-negative");
            adj = (Bag<Integer>[]) new Bag[verticies];
            for (int v = 0; v < verticies; v++) {
                adj[v] = new Bag<Integer>();
            }
            int edges = in.readInt();
            if (edges < 0) throw new IllegalArgumentException("number of edges in a graph must be non-negative");
            for (int i = 0; i < edges; i++) {
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w); 
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in A1Graph constructor", e);
        }
    }


    /**
     * Initializes a new graph that is a deep copy of {@code G}.
     *
     * @param  G the graph to copy
     * @throws IllegalArgumentException if {@code G} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public Graph(Graph G) {
        this.verticies = G.verticies();
        this.edges = G.edges();
        if (verticies < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");

        // update adjacency lists
        adj = (Bag<Integer>[]) new Bag[verticies];
        for (int v = 0; v < verticies; v++) {
            adj[v] = new Bag<Integer>();
        }

        for (int v = 0; v < G.verticies(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) { reverse.push(w); }
            for (int w : reverse) { adj[v].add(w); }
        }
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int verticies() { return verticies; }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int edges() { return edges; }

    private void validateVertex(int v) {
        if (v < 0 || v >= verticies) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (verticies-1));
        }
    }

    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < verticies} and {@code 0 <= w < verticies}
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        edges++;
        adj[v].add(w);
        adj[w].add(v);
    }


    /**
     * Returns the vertices adjacent to vertex.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < verticies}
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < verticies}
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }


    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices, followed by the number of edges, followed by the 
     * adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(verticies + " vertices, " + edges + " edges " + NEWLINE);
        for (int v = 0; v < verticies; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) { s.append(w + " "); }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
    