package darkchoco.msacomponents.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;

import java.util.Objects;

@Getter
public class HotelRoomIdResponse {

    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)  // javascript를 사용하는 애플리케이션을 위해 Long -> String 으로.
    private Long hotelRoomId;

    private HotelRoomIdResponse(Long hotelRoomId) {
        if (Objects.isNull(hotelRoomId))
            throw new IllegalArgumentException("hotelRoomId is null");
        this.hotelRoomId = hotelRoomId;
    }

    public static HotelRoomIdResponse from(Long hotelRoomId) {
        return new HotelRoomIdResponse(hotelRoomId);
    }
}
