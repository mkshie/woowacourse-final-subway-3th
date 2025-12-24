package subway.view;

import java.util.List;
import subway.domain.Station;

public class OutputView {

    private static final String PREFIX = "[INFO] ";

    public void printResult(List<Station> stations, int totalDistance , int totalTime){
        System.out.println("## 조회 결과");
        System.out.println(PREFIX + "---");
        System.out.println(PREFIX + "총 거리: " + totalDistance);
        System.out.println(PREFIX + "총 소요 시간: " + totalTime);
        System.out.println(PREFIX + "---");
        for(Station station : stations){
            System.out.println(PREFIX + station.getName());
        }
        System.out.println();
    }
}
