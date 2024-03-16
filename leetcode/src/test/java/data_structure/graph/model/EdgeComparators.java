package data_structure.graph.model;

import java.util.Comparator;

/**
 * @author zhou.xu
 * @since 2023/11/16 20:36
 */
public class EdgeComparators {

    public static final Comparator<Edge> weightComparator = new EdgeWeightComparator();

    private static class EdgeWeightComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge e1, Edge e2) {
            return e1.weight - e2.weight;
        }
    }
}
