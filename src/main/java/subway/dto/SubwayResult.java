package subway.dto;

import java.util.List;
import subway.domain.Station;

public record SubwayResult(List<Station> path , int totalDistance , int totalTime) {
}
