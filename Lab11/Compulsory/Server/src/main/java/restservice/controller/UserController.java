package restservice.controller;


import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restservice.entity.Person;
import restservice.repository.UserRepository;

@RestController
@RequestMapping(path = "/persons")
public class UserController {

    //tagul @Autowired ii spune Spring-ului sa faca inject unor dependente

    @Autowired
    private UserRepository repository;

    @GetMapping
    public Iterable<Person> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public Person create(@RequestParam String name) {
        Person pers = new Person();
        pers.setName(name);
        return repository.save(pers);
    }

    @DeleteMapping(path = "/{personsId}")
    public ResponseEntity<String> delete(@PathVariable("personsId") Integer id) {
        repository.delete(id);
        return new ResponseEntity<>("Person with id " + id + " deleted", HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> update(@RequestParam String name, @PathVariable Integer id) throws BadHttpRequest {
        if (repository.exists(id)) {
           Person person = repository.findOne(id);
           person.setName(name);
           repository.save(person);
        } else {
            throw new BadHttpRequest();
        }
        return new ResponseEntity<>(
                "Person updated successsfully", HttpStatus.OK);
    }
}
