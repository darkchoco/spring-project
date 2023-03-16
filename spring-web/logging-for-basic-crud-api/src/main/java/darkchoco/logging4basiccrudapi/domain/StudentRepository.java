package darkchoco.logging4basiccrudapi.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Modifying
    @Query(value = "UPDATE Student SET address = ?1 WHERE id = ?2")
    void updateAddressById(String address, Integer Id);
}
