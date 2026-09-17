package darkchoco.essential.dto;

import java.io.Serializable;

/**
 * DTO for {@link darkchoco.essential.entity.Contact}
 */
public record ContactRequestDto(String email, String message, String name, String subject,
                                String userType) implements Serializable {
}