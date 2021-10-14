import java.util.Scanner;

/**
 * A test class which creates a symbol graph out of a file with strings.
 * 
 * @author Malcolm Liljedahl
 * Date: 2021-10-09
 */
public class A3TestMethod {
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
        System.out.println("ENTER INBETWEEN STATION" );
        int middleVertex = sc.nextInt();

        System.out.println("\nThe path from " + symbolGraph.nameOf(sourceVertex) + "(" 
        + sourceVertex + ")" + " through " + symbolGraph.nameOf(middleVertex) + 
        "(" + middleVertex + ")" + " to " + symbolGraph.nameOf(destVertex) + "(" + destVertex + ") : ");

        A3BreadthFirstPath search = new A3BreadthFirstPath(symbolGraph.graph(), sourceVertex);
        if (search.hasPathTo(middleVertex)) {
            for (int x : search.pathTo(middleVertex)) {
                if (x == sourceVertex) { System.out.print(symbolGraph.nameOf(x) + "(" + x + ")"); }
                else { System.out.print("-" + symbolGraph.nameOf(x) + "(" + x + ")"); }
            }
            search = new A3BreadthFirstPath(symbolGraph.graph(), middleVertex);
            if(search.hasPathTo(destVertex)) {
                for (int x : search.pathTo(destVertex)) {
                    if (x == middleVertex) { System.out.print(""); }
                    else { System.out.print("-" + symbolGraph.nameOf(x) + "(" + x + ")"); }
                }
            }
            else { System.out.println("- No path available"); }
            System.out.println();
        }
        else { System.out.println("THIS PATH DOESN'T EXIST"); }

        System.out.println("\nGraph: \n");
        System.out.println(symbolGraph);
        sc.close();
    }
}



    

