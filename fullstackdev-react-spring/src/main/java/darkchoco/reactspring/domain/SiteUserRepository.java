package darkchoco.reactspring.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)  // To Not exposed as a REST resource
public interface SiteUserRepository extends CrudRepository<SiteUser, Long> {
	Optional<SiteUser> findByUsername(String username);
}
