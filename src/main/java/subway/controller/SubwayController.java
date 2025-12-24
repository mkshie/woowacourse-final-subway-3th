package subway.controller;

import java.util.ArrayList;
import java.util.List;
import subway.dto.SubwayResult;
import subway.service.SubwayService;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private final InputView inputView;
    private final OutputView outputView;
    private final SubwayService subwayService;

    public record Information(String startStation, String endStation, int distance, int time) {
    }

    public SubwayController(InputView inputView, OutputView outputView, SubwayService subwayService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.subwayService = subwayService;
    }

    public void run() {
        while (true) {
            String choice = inputView.printMain();

            List<String> choiceAndStations = new ArrayList<>();
            if (choice.equals("1")) {
                choiceAndStations = inputView.printOption();
            }
            if (choice.equals("Q")) {
                return;
            }

            if (choiceAndStations.get(0).equals("B")) {
                continue;
            }

            SubwayResult result;
            String startStationName = choiceAndStations.get(1);
            String endStationName = choiceAndStations.get(2);
            if (choiceAndStations.get(0).equals("1")) {// 최단거리
                result = subwayService.shortestDistance(startStationName, endStationName);

                outputView.printResult(result.path(), result.totalDistance(), result.totalTime());
            }
            if (choiceAndStations.get(0).equals("2")) { //최소 시간
                result = subwayService.shortestTime(startStationName, endStationName);

                outputView.printResult(result.path(), result.totalDistance(), result.totalTime());
            }
        }
    }
}
