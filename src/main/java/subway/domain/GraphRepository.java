package subway.domain;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class GraphRepository {

    private static WeightedMultigraph<Station , DefaultWeightedEdge> distanceGraph;
    private static WeightedMultigraph<Station , DefaultWeightedEdge> timeGraph;

    public static void init(WeightedMultigraph<Station , DefaultWeightedEdge> distance, WeightedMultigraph<Station , DefaultWeightedEdge> time) {
        distanceGraph = distance;
        timeGraph = time;
    }

    public static WeightedMultigraph<Station , DefaultWeightedEdge> getDistanceGraph() {
        return distanceGraph;
    }
    public static WeightedMultigraph<Station , DefaultWeightedEdge> getTimeGraph() {
        return timeGraph;
    }
}
