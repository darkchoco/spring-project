package darkchoco.essential.contact.service.impl;

import darkchoco.essential.contact.service.IContactService;
import darkchoco.essential.dto.ContactRequestDto;
import darkchoco.essential.entity.Contact;
import darkchoco.essential.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ContactServiceImpl implements IContactService {

    private final ContactRepository contactRepository;

    @Override
    public boolean saveContact(ContactRequestDto contactRequestDto) {
        boolean result = false;
        Contact contact = contactRepository.save(convertToEntity(contactRequestDto));
        if (contact.getId() != null) {
            result = true;
        }
        return result;
    }

    private Contact convertToEntity(ContactRequestDto contactRequestDto) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDto, contact);
        contact.setCreatedAt(Instant.now());
        contact.setCreatedBy("SYSTEM");
        contact.setStatus("NEW");
        return contact;
    }
}
