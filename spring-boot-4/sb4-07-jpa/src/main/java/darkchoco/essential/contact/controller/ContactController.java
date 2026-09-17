package darkchoco.essential.contact.controller;

import darkchoco.essential.contact.service.IContactService;
import darkchoco.essential.dto.ContactRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final IContactService contactService;

    @PostMapping(version = "1.0")
    public ResponseEntity<String> saveContactMsg(@RequestBody ContactRequestDto contactRequestDto) {
        boolean isSaved = contactService.saveContact(contactRequestDto);

        if (isSaved)
            return ResponseEntity.status(HttpStatus.CREATED).body("Contact saved successfully");
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save contact");
    }
}
