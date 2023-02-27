package darkchoco.reactspring;

import darkchoco.reactspring.domain.Car;
import darkchoco.reactspring.domain.CarRepository;
import darkchoco.reactspring.domain.Owner;
import darkchoco.reactspring.domain.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class FullstackdevReactSpringApplication implements CommandLineRunner {

    private static final Logger logger =
            LoggerFactory.getLogger(FullstackdevReactSpringApplication.class);

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public static void main(String[] args) {
        SpringApplication.run(FullstackdevReactSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = Owner.builder()
                .firstname("John")
                .lastname("Johnson")
                .build();
        Owner owner2 = Owner.builder()
                .firstname("Mary")
                .lastname("Robinson")
                .build();
        ownerRepository.saveAll(Arrays.asList(owner1, owner2));

        Car car1 = new Car("Ford", "Mustang", "Red","ADF-1121", 2021, 59000, owner1);
        Car car2 = new Car("Nissan", "Leaf", "White","SSJ-3002", 2019, 29000, owner2);
        Car car3 = new Car("Toyota", "Prius", "Silver","KKO-0212", 2020, 39000, owner2);
        carRepository.saveAll(Arrays.asList(car1, car2, car3));

        for (Car car: carRepository.findAll())
            logger.info(car.getBrand() + " " + car.getModel());
    }
}
