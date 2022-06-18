package graph;

public class Test {
    public static void main(String[] argv) throws Exception {
        Graph g = new Graph(4);
        g.addEdge(2,0,1);
        g.addEdge(2,3,4);
        g.addEdge(0,1,1);
        g.addEdge(1,2,1);
        System.out.println(g.hasCircle());
        System.out.println(g.lightestPath(0,3));
    }
}
