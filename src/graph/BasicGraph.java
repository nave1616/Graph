package graph;

import java.util.*;

public abstract class BasicGraph {
    private int size;
    private List<BasicVert> v;

    public BasicGraph() {
        this.size = 0;
        this.v = new ArrayList<>();
    }

    private void inFrame(int source, int dest) throws Exception {
        //Check if source and dest in frame
        if (!(dest >= 0 && dest < size && source >= 0 && source < size))
            throw new Exception(source + " or " + dest + " not exist.");
    }

    private void inFrame(int source) throws Exception {
        //Check if source and dest in frame
        if (!(source >= 0 && source < size))
            throw new Exception(source + " not exist.");
    }

    public void addVert(BasicVert vertex) {
        this.v.add(vertex);
        this.size++;
    }

    public void getVert(int num) throws Exception {
        inFrame(num);
        this.v.get(num);
    }

    public void addEdge(int source, int dest) throws Exception {
        //add edge source -> dest with default weight 0
        this.addEdge(source, dest, 0);
    }

    public void addEdge(int source, int dest, int weight) throws Exception {
        //add edge source -> dest with given weight.
        inFrame(source, dest);
        this.v.get(source).addNeighbor(v.get(dest), weight);
    }

    public void removeEdge(int source, int dest) throws Exception {
        //remove source -> dest edge.
        inFrame(source, dest);
        v.get(source).removeNeighbor(v.get(dest));
    }

    public int getWeight(int source, int dest) throws Exception {
        //get source -> dest edge weight.
        inFrame(source, dest);
        return v.get(source).getWeight(v.get(dest));
    }

    public void setWeight(int source, int dest, int weight) throws Exception {
        //set new weight on source -> edge edge.
        if (weight < 0)
            throw new Exception("weight must be positive.");
        v.get(source).setWeight(v.get(dest), weight);
    }

    public boolean hasPath(int source, int dest) {
        //check if dest available from source.
        return lightestPath(source, dest) < Integer.MAX_VALUE;
    }

    public boolean hasCircle() {
        //return true if graph contain circle, using checkCircle().
        return checkCircle(v.get(0), new HashSet<>());
    }

    private boolean checkCircle(BasicVert v, Set<BasicVert> visited) {
        //return true if graph contain circle.
        visited.add(v);
        for (BasicVert n : v.getNeighbors()) {
            if (visited.contains(n))
                return true;
            return checkCircle(n, visited);
        }
        return false;
    }

    private Map<BasicVert, Integer> initDijkstra(BasicVert source) {
        //Initialize dijkstra ,set weight to infinite.
        Map<BasicVert, Integer> weights = new HashMap<>();
        for (int i = 0; i < size; i++)
            if (v.get(i) != source)
                weights.put(v.get(i), Integer.MAX_VALUE);
        weights.put(source, 0);
        return weights;
    }

    private Map<BasicVert, Integer> dijkstra(BasicVert v, Map<BasicVert, Integer> weights, Set<BasicVert> visited) {
        //Perform dijkstra algorithm.
        if (!visited.contains(v)) {
            visited.add(v);
            for (BasicVert v1 : v.getNeighbors()) {
                if (weights.get(v1) > v.getWeight(v1))
                    weights.replace(v1, (weights.get(v1) < 0 ? 0 : weights.get(v)) + v.getWeight(v1));
                dijkstra(v1, weights, visited);
            }
        }
        return weights;
    }

    public int lightestPath(int source, int dest) {
        //Find path with minimum weight from source to dest.
        Set<BasicVert> visited = new HashSet<>();
        Map<BasicVert, Integer> weights = initDijkstra(v.get(source));
        int shortPath;
        if ((shortPath = dijkstra(v.get(source), weights, visited).get(v.get(dest))) == Integer.MAX_VALUE)
            return -1;
        return shortPath;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            str.append("(Source: ").append(v.get(i));
            if (!v.get(i).getNeighbors().isEmpty()) {
                str.append(", Neighbors: ");
                for (BasicVert n : v.get(i).getNeighbors())
                    str.append("(").append(n).append(", Weight:").append(v.get(i).getWeight(n)).append(")");
            }
            str.append(")\n");
        }
        return str.toString();
    }
}
