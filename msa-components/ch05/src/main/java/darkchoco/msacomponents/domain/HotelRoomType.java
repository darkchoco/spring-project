package darkchoco.msacomponents.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum HotelRoomType {
    SINGLE("single"),
    DOUBLE("double"),
    TRIPLE("triple"),
    QUAD("quad");

    private final String param;

    // 문자열 --> enum 변환을 빠르게 하기 위해 Map으로 캐싱
    private static final Map<String, HotelRoomType> paramMap =
            Arrays.stream(HotelRoomType.values())
                    .collect(Collectors.toMap(HotelRoomType::getParam, Function.identity()));

    // enum 클래스 생성자
    HotelRoomType(String param) {
        this.param = param;
    }

    @JsonCreator  // 언마셜링 과정에서 값 변환에 사용되는 메소드 지정. 예) "triple" -> HotelRoomType.TRIPLE
    public static HotelRoomType fromParam(String param) {
        return Optional.ofNullable(param)
                .map(paramMap::get)
                .orElseThrow(() -> new IllegalArgumentException("param is not valid"));
    }

    @JsonValue  // 마셜링 과정에서 값 변환에 사용되는 메소드를 지정. 예) HotelRoomType.SINGLE -> "single"
    public String getParam() {
        return this.param;
    }
}
