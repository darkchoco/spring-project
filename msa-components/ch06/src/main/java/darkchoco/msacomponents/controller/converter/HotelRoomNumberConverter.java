package darkchoco.msacomponents.controller.converter;

import darkchoco.msacomponents.domain.HotelRoomNumber;
import org.springframework.core.convert.converter.Converter;

public class HotelRoomNumberConverter implements Converter<String, HotelRoomNumber> {

    @Override
    public HotelRoomNumber convert(@SuppressWarnings("NullableProblems") String source) {
        return HotelRoomNumber.parse(source);
    }
}
