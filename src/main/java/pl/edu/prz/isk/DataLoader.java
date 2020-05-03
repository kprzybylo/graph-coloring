package pl.edu.prz.isk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class DataLoader {
    /*
    Loads graph from file.
    File has following structure:
    <number of edges>
    <edge index> <edge source vertice> <edge destination vertice>
    ...
     */
    public static Graph loadData(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(file));

        final Integer numberOfEdges = scanner.nextInt();
        final Set vertices = new HashSet();
        final Map<Integer, Integer> graphEdges = new HashMap<>(numberOfEdges);

        while(scanner.hasNextLine()) {
            scanner.nextInt(); // To ignore index of edge
            final Integer edgeSource = scanner.nextInt();
            final Integer edgeDestination = scanner.nextInt();

            graphEdges.put(edgeSource, edgeDestination);

            vertices.addAll(Set.of(edgeSource, edgeDestination));
        }

        return Graph.builder()
                .graphEdges(graphEdges)
                .graphVertices(vertices)
                .numberOfEdges(numberOfEdges)
                .numberOfVertices(vertices.size())
                .build();
    }
}
