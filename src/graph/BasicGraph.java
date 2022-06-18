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

    public List<BasicVert> getAllVert(){
        return this.v;
    }
    public BasicVert getVert(int num) throws Exception {
        inFrame(num);
        return this.v.get(num);
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
