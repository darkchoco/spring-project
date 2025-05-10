package darkchoco.msacomponents.controller;

import darkchoco.msacomponents.controller.validator.HotelRoomReserveValidator;
import darkchoco.msacomponents.dto.HotelRoomIdResponse;
import darkchoco.msacomponents.dto.HotelRoomReserveRequest;
import darkchoco.msacomponents.service.ReserveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class HotelRoomReserveController {

    private final ReserveService reserveService;

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.addValidators(new HotelRoomReserveValidator());
    }

    @PostMapping(path = "/hotels/{hotelId}/rooms/{roomNumber}/reserve")
    public ResponseEntity<HotelRoomIdResponse> reserveHotelRoomByRoomNumber(
            @PathVariable Long hotelId,
            @PathVariable String roomNumber,
            @Valid @RequestBody HotelRoomReserveRequest reserveRequest,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();

            String errorMessage = null;
            if (fieldError != null)
                errorMessage = "%s [%s] %s".formatted(
                        bindingResult.getFieldError().getCode(),
                        fieldError.getField(),
                        fieldError.getDefaultMessage());

            System.out.println("error: " + errorMessage);
            return ResponseEntity.badRequest().build();
        }

        System.out.println(reserveRequest.toString());

        Long reservationId = reserveService.reserveHotelRoom(
                hotelId,
                roomNumber,
                reserveRequest.getCheckInDate(),
                reserveRequest.getCheckOutDate());

        HotelRoomIdResponse body = HotelRoomIdResponse.from(reservationId);
        return ResponseEntity.ok(body);
    }
}
