package darkchoco.msacomponents.controller.formatter;

import darkchoco.msacomponents.domain.HotelRoomNumber;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class HotelRoomNumberFormatter implements Formatter<HotelRoomNumber> {

    @Override
    public HotelRoomNumber parse(String text, Locale locale) throws ParseException {
        return HotelRoomNumber.parse(text);
    }

    @Override
    public String print(HotelRoomNumber hotelRoomNumber, Locale locale) {
        return hotelRoomNumber.toString();
    }
}
