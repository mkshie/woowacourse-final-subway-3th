package subway.init;

import java.util.List;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.controller.SubwayController.Information;
import subway.domain.GraphRepository;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class SubwayInitializer {
    final static List<String> STATIONS = List.of("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역");
    final static List<String> LINES = List.of("2호선", "3호선", "신분당선");

    static final List<Information> INFOS = List.of(
        new Information("교대역", "강남역", 2, 3),
        new Information("강남역", "역삼역", 2, 3),
        new Information("교대역", "남부터미널역", 3, 2),
        new Information("남부터미널역", "양재역", 6, 5),
        new Information("양재역", "매봉역", 1, 1),
        new Information("강남역", "양재역", 2, 8),
        new Information("양재역", "양재시민의숲역", 10, 3)
    );

    public void init(){
        for (String station : STATIONS) {
            StationRepository.addStation(new Station(station));
        }

        StationRepository.SetUpStationsMap();

        for (String line : LINES) {
            LineRepository.addLine(new Line(line));
        }

        WeightedMultigraph<Station, DefaultWeightedEdge> distanceGraph =
                new WeightedMultigraph<>(DefaultWeightedEdge.class);
        WeightedMultigraph<Station, DefaultWeightedEdge> timeGraph =
                new WeightedMultigraph<>(DefaultWeightedEdge.class);

        List<Station> stations = StationRepository.stations();
        stations.forEach(distanceGraph::addVertex);
        stations.forEach(timeGraph::addVertex);

        for(Information information : INFOS) {
            Station a = StationRepository.findStationByName(information.startStation());
            Station b = StationRepository.findStationByName(information.endStation());

            DefaultWeightedEdge edge = distanceGraph.addEdge(a, b);
            distanceGraph.setEdgeWeight(edge, information.distance());
            DefaultWeightedEdge timeEdge = timeGraph.addEdge(a, b);
            timeGraph.setEdgeWeight(timeEdge, information.time());
        }

        GraphRepository.init(distanceGraph, timeGraph);
    }
}
