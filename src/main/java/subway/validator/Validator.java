package subway.validator;

import java.util.List;
import subway.domain.StationRepository;
import subway.enums.ErrorMessageEnum;

public class Validator {

    public static String validatorMain(String choice) {
        //TODO 입력값 범위 더 세분화 시키기 공백 , 소문자 등등
        if (choice == null || choice.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessageEnum.CHOICE_EMPTY.getMessage());
        }
        if (!choice.equals("1") && !choice.equals("Q")) {
            throw new IllegalArgumentException(ErrorMessageEnum.CHOICE_INVALID_VALUE.getMessage());
        }
        return choice;
    }

    public static String validatorOption(String choice) {

        //TODO 입력값 범위 더 세분화 시키기 공백 , 소문자 등등
        if (choice == null || choice.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessageEnum.CHOICE_EMPTY.getMessage());
        }

        if (!choice.equals("1") && !choice.equals("B") && !choice.equals("2")) {
            throw new IllegalArgumentException(ErrorMessageEnum.CHOICE_INVALID_VALUE.getMessage());
        }

        return choice;
    }

    public static List<String> validatorStationName(String startStationName, String endStationName) {
        if(startStationName.equals(endStationName)){
            throw new IllegalArgumentException(ErrorMessageEnum.SAME_STATION_NAME.getMessage());
        }

        StationRepository.findStationByName(startStationName);
        StationRepository.findStationByName(endStationName);

        return List.of(startStationName , endStationName);
    }
}
