package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Vertex {
    private static int letter = 97;
    private final char name;
    private final Map<Vertex, Integer> edges;

    public Vertex() {
        this.name = (char) letter++;
        this.edges = new HashMap<>();
    }

    public void addNeighbor(Vertex n, int edges) {
        this.edges.put(n, edges);
    }

    public void removeNeighbor(Vertex n) {
        this.edges.remove(n);
    }

    public Set<Vertex> getNeighbors() {
        return this.edges.keySet();
    }

    public boolean isNeighbor(Vertex n) {
        return edges.containsKey(n);
    }

    public String getName() {
        return String.valueOf(name);
    }

    public int getWeight(Vertex dest) {
        if (!edges.containsKey(dest))
            return -1;
        return edges.get(dest);
    }

    public void setWeight(Vertex dest, int weight) {
        if (this.edges.containsKey(dest))
            this.edges.replace(dest, weight);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vertex))
            return false;
        return ((Vertex) o).name == this.name;
    }

    @Override
    public int hashCode() {
        return this.name;
    }

    public String toString() {
        return getName();
    }
}
