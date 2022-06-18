package graph;

import java.util.*;

public class Graph extends BasicGraph{
    public Graph(int size){
        super();
        for (int i=0;i<size;i++)
            super.addVert(new Vertex());
    }
}
