package darkchoco.msacomponents.dto;

import darkchoco.msacomponents.domain.HotelRoomType;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class HotelRoomRequest {

    private String roomNumber;
    private HotelRoomType roomType;  // JSON request의 roomType 속성과 매칭되는 클래스 속성이므로 이름을 roomType으로.
    private BigDecimal originalPrice;
}
