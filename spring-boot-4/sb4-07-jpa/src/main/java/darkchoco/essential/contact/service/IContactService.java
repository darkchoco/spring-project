package darkchoco.essential.contact.service;

import darkchoco.essential.dto.ContactRequestDto;

public interface IContactService {

    boolean saveContact(ContactRequestDto contactRequestDto);
}
