package pl.edu.prz.isk;

import com.softtechdesign.ga.ChromStrings;
import com.softtechdesign.ga.Crossover;
import com.softtechdesign.ga.GAException;
import com.softtechdesign.ga.GAStringsSeq;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GraphPainter extends GAStringsSeq {
    Graph graph;

    public GraphPainter(Graph graph) throws GAException {
        super(
                graph.getNumberOfEdges(), //size of chromosome
                200, //population has N chromosomes
                0.4, //crossover probability
                10, //random selection chance % (regardless of fitness)
                1000, //max generations
                0, //num prelim runs (to build good breeding stock for final/full run)
                20, //max generations per prelim run
                0.4, //chromosome mutation prob.
                0, //number of decimal places in chrom
                ColorsProvider.getColors(graph.getNumberOfEdges()), //gene space (possible gene values)
                Crossover.ctTwoPoint, //crossover type
                true //compute statisitics
        );

        this.graph = graph;
    }

    @Override
    protected double getFitness(int i) {
        // check if color is used more than once
        if (graph != null) {
            ChromStrings chromosome = getChromosome(i);
            String genes[] = chromosome.getGenes();
            Set<String> usedColors = new HashSet<>(Arrays.asList(genes));
            AtomicReference<Integer> verticesWithIncorrectColoring = new AtomicReference<>(0);

            this.graph.getGraphVertices().forEach(vertice -> {
                List<Edge> verticeEdges = this.graph.getGraphEdges()
                        .stream()
                        .filter(edge -> edge.getSource() == vertice || edge.getDestination() == vertice)
                        .collect(Collectors.toList());

                for(int a = 0; a < verticeEdges.size(); a++) {
                    for(int b = 0; b < verticeEdges.size(); b++) {
                        if(a != b) {
                            Integer edgeIndex1 = graph.getGraphEdges().indexOf(verticeEdges.get(a));
                            Integer edgeIndex2 = graph.getGraphEdges().indexOf(verticeEdges.get(b));

                            if (genes[edgeIndex1].equals(genes[edgeIndex2])) {
                                verticesWithIncorrectColoring.getAndSet(verticesWithIncorrectColoring.get() + 1);
                            }
                        }
                    }
                }
            });

            return graph.getNumberOfEdges() - verticesWithIncorrectColoring.get() - usedColors.size();
        }
        return 0;
    }
}
