package pl.edu.prz.isk

import spock.lang.Specification

class DataLoaderSpec extends Specification {
    def "should load graph properly"() {
        given: "there is file describing graph"
        File fileName = new File(this.class.getClassLoader().getResource('graph.txt').toURI())

        when: "graph is loaded form file"
        Graph result = DataLoader.loadData(fileName);

        then: "graph should have proper number of edges and vertices"
        result.getNumberOfEdges() == 10
        result.getNumberOfVertices() == 7
    }
}
