package subway.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import subway.validator.Validator;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String printMain() {
        while (true) {
            try {
                System.out.println("## 메인 화면");
                System.out.println("1. 경로 조회");
                System.out.println("Q. 종료");

                printChoice();

                String choice = this.scanner.nextLine();
                return Validator.validatorMain(choice);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<String> printOption() {
        while (true) {
            try {
                System.out.println("## 경로 기준");
                System.out.println("1. 최단 거리");
                System.out.println("2. 최소 시간");
                System.out.println("B. 돌아가기");

                printChoice();

                String choice = this.scanner.nextLine();
                choice = Validator.validatorOption(choice);

                if(choice.equals("B")) {
                    return List.of(choice);
                }

                List<String> choiceAndStation = new ArrayList<>();
                choiceAndStation.add(choice);

                choiceAndStation.addAll(printStation());

                return choiceAndStation;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printChoice() {
        System.out.println();
        System.out.println("## 원하는 기능을 선택하세요");
    }

    private List<String> printStation() {
        System.out.println("## 출발역을 입력하세요.");
        String startStationName = this.scanner.nextLine();
        System.out.println();
        System.out.println("## 도착역을 입력하세요");
        String endStationName = this.scanner.nextLine();
        System.out.println();

        return Validator.validatorStationName(startStationName, endStationName);
    }
}
