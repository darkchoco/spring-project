package darkchoco.msacomponents.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.StringJoiner;

@AllArgsConstructor
@Getter
public class HotelRoomNumber {

    private static final String DELIMITER = "-";

    private String buildingCode;
    private Long roomNumber;

    public static HotelRoomNumber parse(String roomNumberId) {
        String[] tokens = roomNumberId.split(DELIMITER);
        if (tokens.length != 2)
            throw new IllegalArgumentException("Invalid roomNumberId format");

        return new HotelRoomNumber(tokens[0], Long.parseLong(tokens[1]));
    }

    @Override
    public String toString() {
        return new StringJoiner(DELIMITER)
                .add(buildingCode)
                .add(roomNumber.toString())
                .toString();
    }
}
