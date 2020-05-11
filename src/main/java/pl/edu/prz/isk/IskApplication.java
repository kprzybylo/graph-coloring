package pl.edu.prz.isk;

import com.softtechdesign.ga.GAException;

import java.io.File;
import java.io.FileNotFoundException;

public class IskApplication {
    public static void main(String[] args) throws FileNotFoundException, GAException, InterruptedException {
        File file = new File(IskApplication.class.getClassLoader().getResource("graph.txt").getFile());
        Graph graph = DataLoader.loadData(file);
        GraphPainter graphPainter = new GraphPainter(graph);
        Thread graphThread = new Thread(graphPainter);
        graphThread.setPriority(Thread.MAX_PRIORITY);
        graphThread.start();
        graphThread.join();
        if(!graphThread.isAlive()) {
            System.out.println(graphPainter.getFittestChromosome());
        }
    }
}
