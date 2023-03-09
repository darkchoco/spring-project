package darkchoco.reactspring.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void OwnerShouldExistWhenSavingIt() {
        ownerRepository.save(Owner.builder()
                        .firstname("수정")
                        .lastname("김")
                        .build());
        assertThat(ownerRepository.findByFirstname("수정").isPresent()).isTrue();
    }

    @Test
    void NoDataExistWhenDeletingAll() {
        ownerRepository.save(Owner.builder()
                .firstname("수정")
                .lastname("김")
                .build());
        ownerRepository.save(Owner.builder()
                .firstname("Michael")
                .lastname("Jackson")
                .build());
        ownerRepository.deleteAll();
        assertThat(ownerRepository.count()).isEqualTo(0);
    }
}
