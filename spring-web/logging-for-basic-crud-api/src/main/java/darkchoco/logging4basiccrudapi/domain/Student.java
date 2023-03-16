package darkchoco.logging4basiccrudapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString
@Getter
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

//    @Column(name = "Name")
    private String studentName;

//    @Column(name = "Age")
    private String studentAge;

//    @Column(name = "Address")
    private String address;
}
