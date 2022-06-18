package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class BasicVert {

    private static int letter = 97;
    private final char name;
    private final Map<BasicVert, Integer> edges;

    public BasicVert() {
        this.name = (char) letter++;
        this.edges = new HashMap<>();
    }

    public void addNeighbor(BasicVert n, int weight) {
        this.edges.put(n, weight);
    }

    public void removeNeighbor(BasicVert n) {
        this.edges.remove(n);
    }

    public Set<BasicVert> getNeighbors() {
        return this.edges.keySet();
    }

    public boolean isNeighbor(BasicVert n) {
        return edges.containsKey(n);
    }

    public String getName() {
        return String.valueOf(name);
    }

    public int getWeight(BasicVert dest) {
        if (!edges.containsKey(dest))
            return -1;
        return edges.get(dest);
    }

    public void setWeight(BasicVert dest, int weight) {
        if (this.edges.containsKey(dest))
            this.edges.replace(dest, weight);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BasicVert))
            return false;
        return ((BasicVert) o).name == this.name;
    }

    @Override
    public int hashCode() {
        return this.name;
    }

    public String toString() {
        return getName();
    }
}

