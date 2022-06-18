package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Vertex extends BasicVert {
    private Vertex reveal;
    public Vertex(){
        super();
        this.reveal = null;
    }

    public void setReveal(Vertex v){
        this.reveal = v;
    }
}
