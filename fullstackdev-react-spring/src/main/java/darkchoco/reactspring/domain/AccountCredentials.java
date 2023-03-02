package darkchoco.reactspring.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * POJO class to keep credentials for authentication
 */
@Setter
@Getter
public class AccountCredentials {
	private String username;
	private String password;
}
