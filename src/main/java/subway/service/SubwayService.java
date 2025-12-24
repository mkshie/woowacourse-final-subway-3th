package subway.service;

import java.util.List;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.GraphRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.dto.SubwayResult;

public class SubwayService {
    private final WeightedMultigraph<Station, DefaultWeightedEdge> distanceGraph;
    private final WeightedMultigraph<Station, DefaultWeightedEdge> timeGraph;

    private final DijkstraShortestPath<Station , DefaultWeightedEdge> dijkstraShortestDistancePath;
    private final DijkstraShortestPath<Station , DefaultWeightedEdge> dijkstraShortestTimePath;

    public SubwayService() {
        this.distanceGraph = GraphRepository.getDistanceGraph();
        this.timeGraph = GraphRepository.getTimeGraph();

        if (distanceGraph == null || timeGraph == null) {
            throw new IllegalStateException("그래프 초기화 필요합니다.");
        }

        this.dijkstraShortestDistancePath = new DijkstraShortestPath<>(distanceGraph);
        this.dijkstraShortestTimePath = new DijkstraShortestPath<>(timeGraph);
    }

    public SubwayResult shortestDistance(String startStation, String endStation) {
        return find(dijkstraShortestDistancePath , startStation , endStation);
    }

    public SubwayResult shortestTime(String startStation, String endStation) {
        return find(dijkstraShortestTimePath , startStation , endStation);
    }

    private SubwayResult find(DijkstraShortestPath<Station , DefaultWeightedEdge> dijkstra , String startStation, String endStation) {

        Station start = StationRepository.findStationByName(startStation);
        Station end = StationRepository.findStationByName(endStation);

        GraphPath<Station , DefaultWeightedEdge> path = dijkstra.getPath(start, end);
        if(path == null) {
            throw new IllegalArgumentException("해당 경로는 존재하지 않는 경로입니다.");
        }

        List<Station> vertices = path.getVertexList();

        int totalDistance = 0;
        int totalTime = 0;

        for(int i = 0; i < vertices.size() - 1; i++) {
            Station a = vertices.get(i);
            Station b = vertices.get(i + 1);
            totalDistance += (int) distanceGraph.getEdgeWeight(distanceGraph.getEdge(a, b));
            totalTime += (int) timeGraph.getEdgeWeight(timeGraph.getEdge(a, b));
        }

        return new SubwayResult(vertices, totalDistance, totalTime);
    }
}
