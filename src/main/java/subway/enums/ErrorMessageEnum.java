package subway.enums;

public enum ErrorMessageEnum {
    CHOICE_INVALID_VALUE("기능 선택 입력값이 잘못됐습니다."),
    CHOICE_EMPTY("입력값이 비어있습니다."),
    SAME_STATION_NAME("출발역과 도착역이 동일합니다."),
    ;


    private final String message;
    private static final String PREFIX = "[ERROR] ";


    ErrorMessageEnum(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }


}
