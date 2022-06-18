package graph;

import java.util.*;

public class Graph {
    private final Vertex[] g;
    private final int size;

    public Graph(int size) {
        this.g = new Vertex[size];
        for (int i = 0; i < size; i++)
            g[i] = new Vertex();
        this.size = size;
    }

    private boolean inFrame(int source, int dest) {
        //Check if source and dest in frame
        return dest >= 0 && dest < size && source >= 0 && source < size;
    }

    public void addEdge(int source, int dest) throws Exception {
        //add edge source -> dest with default weight 0
        if (!inFrame(source, dest))
            throw new Exception(source + " or " + dest + " not exist.");
        g[source].addNeighbor(g[dest], 0);
    }

    public void addEdge(int source, int dest, int weight) throws Exception {
        //add edge source -> dest with given weight.
        if (!inFrame(source, dest))
            throw new Exception(source + " or " + dest + " not exist.");
        if (weight < 0)
            throw new Exception("weight must be positive.");
        g[source].addNeighbor(g[dest], weight);
    }

    public void removeEdge(int source, int dest) {
        //remove source -> dest edge.
        if (inFrame(source, dest))
            g[source].removeNeighbor(g[dest]);
    }

    public int getWeight(int source, int dest) {
        //get source -> dest edge weight.
        return g[source].getWeight(g[dest]);
    }

    public void setWeight(int source, int dest, int weight) throws Exception {
        //set new weight on source -> edge edge.
        if (weight < 0)
            throw new Exception("weight must be positive.");
        g[source].setWeight(g[dest], weight);
    }

    public boolean hasPath(int source, int dest) {
        //check if dest available from source.
        return lightestPath(source, dest) < Integer.MAX_VALUE;
    }

    public boolean hasCircle() {
        //return true if graph contain circle, using checkCircle().
        return checkCircle(g[0], new HashSet<>());
    }

    private boolean checkCircle(Vertex v, Set<Vertex> visited) {
        //return true if graph contain circle.
        visited.add(v);
        for (Vertex n : v.getNeighbors()) {
            if (visited.contains(n))
                return true;
            return checkCircle(n, visited);
        }
        return false;
    }

    private Map<Vertex, Integer> initDijkstra(Vertex source) {
        //Initialize dijkstra ,set weight to infinite.
        Map<Vertex, Integer> weights = new HashMap<>();
        for (int i = 0; i < size; i++)
            if (g[i] != source)
                weights.put(g[i], Integer.MAX_VALUE);
        weights.put(source, 0);
        return weights;
    }

    private Map<Vertex, Integer> dijkstra(Vertex v, Map<Vertex, Integer> weights, Set<Vertex> visited) {
        //Perform dijkstra algorithm.
        if (!visited.contains(v)) {
            visited.add(v);
            for (Vertex v1 : v.getNeighbors()) {
                if (weights.get(v1) > v.getWeight(v1))
                    weights.replace(v1, (weights.get(v1) < 0 ? 0 : weights.get(v)) + v.getWeight(v1));
                dijkstra(v1, weights, visited);
            }
        }
        return weights;
    }

    public int lightestPath(int source, int dest) {
        //Find path with minimum weight from source to dest.
        Set<Vertex> visited = new HashSet<>();
        Map<Vertex, Integer> weights = initDijkstra(g[source]);
        int shortPath;
        if ((shortPath = dijkstra(g[source], weights, visited).get(g[dest])) == Integer.MAX_VALUE)
            return -1;
        return shortPath;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            str.append("(Source: ").append(g[i]);
            if (!g[i].getNeighbors().isEmpty()) {
                str.append(", Neighbors: ");
                for (Vertex n : g[i].getNeighbors())
                    str.append("(").append(n).append(", Weight:").append(g[i].getWeight(n)).append(")");
            }
            str.append(")\n");
        }
        return str.toString();
    }
}
