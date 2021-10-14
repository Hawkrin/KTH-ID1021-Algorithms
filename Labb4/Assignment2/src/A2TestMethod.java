import java.util.Scanner;

/**
 * A test class which creates a symbol graph out of a file with strings.
 * 
 * @author Malcolm Liljedahl
 * Date: 2021-10-09
 */
public class A2TestMethod {
    private static final String FILEPATH = "C:\\Users\\malco\\OneDrive\\Documents\\KTH\\KURSER\\Algo_data\\Labb4\\Assignment1\\database.txt";
    private static String delimiter = " ";

    /**
     * Test method which checks if there's a path between two verticies specified by the user,
     * if a path exists, the path is then printed.
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

        A2BreadthFirstPath search = new A2BreadthFirstPath(symbolGraph.graph(), sourceVertex);
        
        System.out.println("\nThe path from: " + symbolGraph.nameOf(sourceVertex) + "(" 
        + sourceVertex + ")" +  " to " + symbolGraph.nameOf(destVertex) + "(" + destVertex + ") : ");
        
        if(search.hasPathTo(destVertex)) {
            for (int vertex : search.pathTo(destVertex)) {
                if (vertex == sourceVertex) { System.out.print(symbolGraph.nameOf(vertex) + "(" + vertex + ")"); }
                else { System.out.print("-" + symbolGraph.nameOf(vertex) + "(" + vertex + ")"); }
            }
        }
        else { System.out.println("There's no path from " + sourceVertex + " to " + destVertex );}
        System.out.println();

        System.out.println("\nGRAPH: \n");
        System.out.println(symbolGraph);
        sc.close();
    }


}
    

