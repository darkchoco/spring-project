package darkchoco.msacomponents.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import darkchoco.msacomponents.util.IdGenerator;
import darkchoco.msacomponents.util.ToDollarStringSerializer;
import darkchoco.msacomponents.domain.HotelRoomType;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class HotelRoomResponse {

    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)  // 마셜링 과정에서 Long 값을 String 타입으로 변경
    private final Long hotelRoomId;

    private final String roomNumber;

    private final HotelRoomType hotelRoomType;

    @JsonSerialize(using = ToDollarStringSerializer.class)
    private final BigDecimal originalPrice;

    private final List<Reservation> reservations;

    private HotelRoomResponse(Long hotelRoomId, String roomNumber, HotelRoomType hotelRoomType, BigDecimal originalPrice) {
        this.hotelRoomId = hotelRoomId;
        this.roomNumber = roomNumber;
        this.hotelRoomType = hotelRoomType;
        this.originalPrice = originalPrice;
        reservations = new ArrayList<>();
    }

    public static HotelRoomResponse of(
            Long hotelRoomId, String roomNumber, HotelRoomType hotelRoomType, BigDecimal originalPrice) {
        return new HotelRoomResponse(hotelRoomId, roomNumber, hotelRoomType, originalPrice);
    }

    public void reservedAt(LocalDate reservedAt) {
        reservations.add(new Reservation(IdGenerator.create(), reservedAt));
    }

    // Record
    private record Reservation(@JsonProperty("id") @JsonSerialize(using = ToStringSerializer.class) Long reservationId,
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate reservedDate) {
    }
}
