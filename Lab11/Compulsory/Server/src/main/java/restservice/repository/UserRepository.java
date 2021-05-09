package restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import restservice.entity.Person;

@RestResource(exported = false)
public interface UserRepository extends JpaRepository<Person, Integer> {

}
