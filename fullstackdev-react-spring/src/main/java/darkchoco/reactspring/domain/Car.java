package darkchoco.reactspring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Car {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String brand;
    private String model;
    private String color;
    private String registerNumber;
	private int manufacturedYear;
	private int price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner")
	private Owner owner;

    public Car(String brand, String model, String color, String registerNumber, int manufacturedYear, int price, Owner owner) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.manufacturedYear = manufacturedYear;
        this.price = price;
        this.owner = owner;
    }
}
