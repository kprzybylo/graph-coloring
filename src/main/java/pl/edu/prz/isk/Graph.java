package pl.edu.prz.isk;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Set;

@Value
@Builder
public class Graph {
    Integer numberOfVertices;
    Integer numberOfEdges;
    Set<Integer> graphVertices;
    List<Edge> graphEdges;
}
