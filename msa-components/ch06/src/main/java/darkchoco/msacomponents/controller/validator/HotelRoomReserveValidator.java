package darkchoco.msacomponents.controller.validator;


import darkchoco.msacomponents.dto.HotelRoomReserveRequest;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@NonNullApi
public class HotelRoomReserveValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return HotelRoomReserveRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HotelRoomReserveRequest request = (HotelRoomReserveRequest) target;

        if (Objects.isNull(request.getCheckInDate())) {
            errors.rejectValue("checkInDate", "NotNull", "checkInDate is null");
            return;
        }

        if (Objects.isNull(request.getCheckOutDate())) {
            errors.rejectValue("checkOutDate", "NotNull", "checkOutDate is null");
            return;
        }

        if (!request.getCheckInDate().isBefore(request.getCheckOutDate())) {
            errors.rejectValue("checkOutDate", "Constraint Error", "checkOutDate is earlier than checkInDate ");
        }
    }
}
