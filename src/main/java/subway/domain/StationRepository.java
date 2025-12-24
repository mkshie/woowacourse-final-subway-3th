package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();
    private static Map<String , Station> stationMap = new HashMap<>();

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static void addStation(Station station) {
        stations.add(station);
    }

    public static void SetUpStationsMap(){
        stationMap = stations.stream().collect(Collectors.toMap(Station::getName , stations -> stations));
    }

    public static Station findStationByName(String name) {
        if(name == null || !stationMap.containsKey(name)) {
            throw new IllegalArgumentException("존재하지 않는 역 이름입니다.");
        }
        return stationMap.get(name);
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    public static void deleteAll() {
        stations.clear();
    }
}
