package darkchoco.reactspring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class SiteUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, updatable=false)
	private long id;

	@Column(nullable=false, unique=true)
	private String username;
	 
	@Column(nullable=false)
	private String password;

	@Column(nullable=false)
	private String role;

    @Builder
	public SiteUser(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
}
