import edu.princeton.cs.algs4.Stack;

/**
 * This class represents a data type for determining if a directed graph has a cycle/loop or not
 * The implementation uses depth-first search and is based on princetons DirectedCycle.java
 * 
 * @author Malcolm Liljedahl
 * Date: 2021-10-09
 */
public class A5DirectedCycle {
    private boolean[] marked;      
    private int[] from;             
    private boolean[] onStack;       // onStack[vertex] = is vertex on the stack
    private Stack<Integer> cycle;    // directed cycle (or null if no such cycle)

    /**
     * Determines whether the digraph has a directed cycle and, if so, finds such a cycle.
     * 
     * @param graph the graph
     */
    public A5DirectedCycle(Digraph graph) {
        marked  = new boolean[graph.V()];
        onStack = new boolean[graph.V()];
        from  = new int[graph.V()];
        for (int vertex = 0; vertex < graph.V(); vertex++)
            if (!marked[vertex] && cycle == null) { dfs(graph, vertex); }
    }

    // run DFS and find a directed cycle (if one exists)
    private void dfs(Digraph graph, int vertex) {
        onStack[vertex] = true;
        marked[vertex] = true;
        for (int w : graph.adj(vertex)) {

            // short circuit if directed cycle found
            if (cycle != null) { return; }

            // found new vertex, so recur
            else if (!marked[w]) {
                from[w] = vertex;
                dfs(graph, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = vertex; x != w; x = from[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(vertex);
            }
        }
        onStack[vertex] = false;
    }

    /**
     * Checks if the graph has a cycle
     * @return true if the graph has a directed cycle, otherwise null.
     */
    public boolean hasCycle() { return cycle != null; }

    /**
     * Returns a directed cycle if the digraph has a directed cycle, otherwise null.
     * @return a directed cycle (as an iterable) otherwise null
     */
    public Iterable<Integer> cycle() { return cycle; }

}