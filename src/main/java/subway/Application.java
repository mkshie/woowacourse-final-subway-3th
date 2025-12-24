package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
import subway.init.SubwayInitializer;
import subway.service.SubwayService;
import subway.view.InputView;
import subway.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();
        SubwayInitializer subwayInitializer = new SubwayInitializer();
        subwayInitializer.init();

        SubwayService subwayService = new SubwayService();

        SubwayController subwayController = new SubwayController(inputView,outputView , subwayService);
        subwayController.run();
    }
}
