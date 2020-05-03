package pl.edu.prz.isk;

import lombok.Builder;
import lombok.Value;

import java.util.Map;
import java.util.Set;

@Value
@Builder
public class Graph {
    private final Integer numberOfVertices;
    private final Integer numberOfEdges;
    private final Set<Integer> graphVertices;
    private final Map<Integer, Integer> graphEdges;
}
