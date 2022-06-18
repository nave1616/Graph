package graph;

import java.util.*;

public class Graph extends BasicGraph {
    private int size;

    public Graph(int size) {
        super();
        this.size = size;
        for (int i = 0; i < size; i++)
            super.addVert(new Vertex());
    }

    private Map<BasicVert, Integer> initDijkstra(BasicVert source) {
        //Initialize dijkstra ,set weight to infinite.
        Map<BasicVert, Integer> weights = new HashMap<>();
        for (int i = 0; i < size; i++)
            if (getAllVert().get(i) != source)
                weights.put(getAllVert().get(i), Integer.MAX_VALUE);
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
        Map<BasicVert, Integer> weights = initDijkstra(getAllVert().get(source));
        int shortPath;
        if ((shortPath = dijkstra(getAllVert().get(source), weights, visited).get(getAllVert().get(dest))) == Integer.MAX_VALUE)
            return -1;
        return shortPath;
    }

    public boolean hasPath(int source, int dest) {
        //check if dest available from source.
        return lightestPath(source, dest) < Integer.MAX_VALUE;
    }

    public boolean hasCircle() {
        //return true if graph contain circle, using checkCircle().
        return checkCircle(getAllVert().get(0), new HashSet<>());
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
}
