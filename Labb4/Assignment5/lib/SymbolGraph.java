import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * This class represents a symbol graph based on princetons SymbolGraph.java
 * 
 * @author Malcolm Liljedahl
 * Date: 2021-10-09
 */
public class SymbolGraph {
    private ST<String, Integer> symbolTable; 
    private String[] keys; 
    private Digraph graph;

    /**
     * Initializes a graph from a file(and specified delimiter).
     * 
     * @param filename the file the graph will represent
     * @param delimiter the delimeter between the vericies.
     */
    public SymbolGraph(String filename, String delimiter) {
        symbolTable = new ST<String, Integer>();
        //Builds a symbol table by setting unique values to strings
        In in = new In(filename);
        while (!in.isEmpty()) {
            String[] string = in.readLine().split(delimiter);
            for (int i = 0; i < string.length; i++) {
                if (!symbolTable.contains(string[i])) {
                    symbolTable.put(string[i], symbolTable.size());
                }
            }
        }
        // Invert index to get string keys in an array
        keys = new String[symbolTable.size()];
        for (String name : symbolTable.keys()) {
            keys[symbolTable.get(name)] = name;
        }
        //Bulding graph by adding edges from the first index to the adjecent vertices
        //on each line.
        graph = new Digraph(symbolTable.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = symbolTable.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = symbolTable.get(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    /**
     * Checks if the graph contains a specific vertex by looking through the symbol table.
     * 
     * @param vertex the name of a vertex
     * @return true if graph contains vertex otherwise false
     */
    public boolean contains(String vertex) { return symbolTable.contains(vertex); }

    /**
     * Returns the integer associated with the vertex.
     * 
     * @param vertex the name of the vertex.
     * @return the integer associated with the vertex.
     */
    public int indexOf(String vertex) { return symbolTable.get(vertex); }

    /**
     * Returns the name of the vertex associated with the integer.
     * 
     * @param  i the integer corresponding to a vertex
     * @return the name of the vertex associated with the integer
     * @throws IllegalArgumentException if i is out of bound.
     */
    public String nameOf(int i) {
        validateVertex(i);
        return keys[i];
    }
    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }

    /**
     * Returns the Digraph assoicated with the symbol graph.
     * 
     * @return the graph associated with the symbol graph
     */
    public Digraph diGraph() { return graph; }


    /**
     * Prints integer verticies as strings.
     * 
     * @return the number of vertices, followed by the number of edges, followed by the 
     * adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int v = 0; v < this.diGraph().V(); v++) {
            s.append(this.nameOf(v) + "(" + v + ")" + ": ");
            for (int w : this.diGraph().adj(v)) {
                s.append(this.nameOf(w) + "(" + w + ")" + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    
  
}


