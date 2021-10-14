import java.util.Scanner;

/**
 * A test class which creates a symbol graph out of a file with strings.
 * 
 * @author Malcolm Liljedahl
 * Date: 2021-10-09
 */
public class A4TestMethod {
    private static final String FILEPATH = "C:\\Users\\malco\\OneDrive\\Documents\\KTH\\KURSER\\Algo_data\\Labb4\\Assignment1\\database.txt";
    private static String delimiter = " ";

    /**
     * Test method which checks if there's a path between two verticies specified by the user,
     * if a path exists, the path is then printed.
     * 
     * Example that exist in path : 7->26
     * 
     * @param args -- 
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SymbolGraph symbolGraph = new SymbolGraph(FILEPATH, delimiter);

        System.out.println("ENTER SOURCE ");
        int sourceVertex = sc.nextInt();
        System.out.println("ENTER DESTINATION ");
        int destVertex = sc.nextInt();

        A4DepthFirstPath search = new A4DepthFirstPath(symbolGraph.graph(), sourceVertex);
        if(search.hasPathTo(destVertex)) {
            System.out.println("\nThe path from: " + symbolGraph.nameOf(sourceVertex) + "(" 
            + sourceVertex + ")" +  " to " + symbolGraph.nameOf(destVertex) + "(" + destVertex + ") : ");
            for (int vertex : search.pathTo(destVertex)) {
                if (vertex == sourceVertex) { System.out.print(symbolGraph.nameOf(vertex) + "(" + vertex + ")"); }
                else { System.out.print("->" + symbolGraph.nameOf(vertex) + "(" + vertex + ")"); }
            }
        }
        else {
            System.out.println("\nThe path from: " + symbolGraph.nameOf(sourceVertex) + "(" 
            + sourceVertex + ")" +  " -> " + symbolGraph.nameOf(destVertex) + "(" + destVertex + 
            ")" + " does not exist");
        }
        
        System.out.println();
        
        System.out.println("\nGRAPH: \n");
        System.out.println(symbolGraph);
        sc.close();




    }
}
