package darkchoco.reactspring.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@NoArgsConstructor
@Entity
public class Owner {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long ownerid;

	private String firstname, lastname;

    @JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="owner")
	private List<Car> cars;
	
	public void setCars(List<Car> cars)  {
		this.cars = cars;
	}

    @Builder
    public Owner(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
