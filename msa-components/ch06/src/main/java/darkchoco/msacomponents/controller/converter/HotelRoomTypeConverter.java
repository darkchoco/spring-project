package darkchoco.msacomponents.controller.converter;

import darkchoco.msacomponents.domain.HotelRoomType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HotelRoomTypeConverter implements Converter<String, HotelRoomType> {

    @Override
    public HotelRoomType convert(@SuppressWarnings("NullableProblems") String source) {
        return HotelRoomType.fromParam(source);
    }
}
