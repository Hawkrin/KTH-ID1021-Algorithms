import java.util.Scanner;

/**
 * A test class that reads a file and creates a graph out of it. Then it checks if there're any
 * loops in the graph.
 * 
 * @author Malcolm Liljedahl
 * Date: 2021-10-09
 */
public class A5TestMethod {
    private static final String FILEPATH = "C:\\Users\\malco\\OneDrive\\Documents\\KTH\\KURSER\\Algo_data\\Labb4\\Assignment1\\database.txt";
    private static final String FILEPATHWITHCYCLE = "C:\\Users\\malco\\OneDrive\\Documents\\KTH\\KURSER\\Algo_data\\Labb4\\databaseWithCycle.txt";
    private static String delimiter = " ";
     
    /**
     * Test method that checks if a graph has any loops in it. It also manipulates the graph
     * by constructing a loop.
     *
     * @param args --
     */
    public static void main(String[] args) {
        SymbolGraph symbolGraph = new SymbolGraph(FILEPATH, delimiter);
        SymbolGraph symbolGraph2 = new SymbolGraph(FILEPATHWITHCYCLE, delimiter);
        
        //checkForCycle(symbolGraph);
        //checkForModifiedCycle(symbolGraph2);
        modifyCycle(symbolGraph2);
    
    
        System.out.println();
        
    }

    /**
     * Looks for a cycle in the provided "database.txt" file.
     * 
     * @param symbolGraph the symbolgraph
     */
    public static void checkForCycle(SymbolGraph symbolGraph) {
        A5DirectedCycle searchForCycle = new A5DirectedCycle(symbolGraph.diGraph());

        if (searchForCycle.hasCycle()) {
            System.out.print("Directed cycle: ");
            for (int v : searchForCycle.cycle()) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
        else { System.out.println("No directed cycle"); }
        System.out.println();

        System.out.println("ORIGINAL GRAPH: ");
        System.out.println(symbolGraph);
    }

    /**
     * Looks for a graph in a new version of the database.txt file where a cycle 
     * has been implemented
     * An edge between TN(4) and FL(1) has been added 
     * 
     * @param symbolGraph2 the modified symbolgraph
     */
    public static void checkForModifiedCycle(SymbolGraph symbolGraph2) {
        A5DirectedCycle searchForCycle = new A5DirectedCycle(symbolGraph2.diGraph());

        if (searchForCycle.hasCycle()) {
            System.out.print("Directed cycle: ");
            for (int i : searchForCycle.cycle()) {
                System.out.print(symbolGraph2.nameOf(i) + "->");
            }
            System.out.println();
        } 
        else { System.out.println("No directed cycle was found."); }
        System.out.println();

        System.out.println("MODIFIED GRAPH: ");
        System.out.println(symbolGraph2);
    }

    /**
     * Adds an edge between two elements and then search the graph for a cycle.
     * An edge between TN(4) and FL(1) has been added 
     * 
     * @param symbolGraph the modified symbolgraph
     */
    public static void modifyCycle(SymbolGraph symbolGraph2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a source vertex: ");
        int sourceVertex = sc.nextInt();
        boolean sourceInCycle = false;

        A5DirectedCycle searchForCycle = new A5DirectedCycle(symbolGraph2.diGraph());
        if (searchForCycle.hasCycle()) {
            System.out.print("Directed cycle: ");
            for (int i : searchForCycle.cycle()) {
                if(i == sourceVertex) { sourceInCycle = true; }   
            }
            if(sourceInCycle)  {
                for (int i : searchForCycle.cycle()) {
                    System.out.print(symbolGraph2.nameOf(i) + "(" + i + ")" + "->");
                }
                System.out.println();
            }
            else { System.out.println("No cycle was found through the specified vertex."); }      
        } 
        else { System.out.println("The graph doesn't contain a cycle"); }
        
        System.out.println();

        System.out.println("ORIGINAL GRAPH: ");
        System.out.println(symbolGraph2.toString());
        sc.close();
    }
}
