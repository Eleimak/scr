package tam.edu.scr.controllrer.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tam.edu.scr.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonRESTController {
    private List<Person> personList = new ArrayList<>(
            Arrays.asList(
                    new Person("1","The first", 23),
                    new Person("2","The second", 21),
                    new Person("3","The third", 25)
            )
    );

    @RequestMapping("/get/list")
    public List<Person> showAll(){
        return personList;
    }

    @RequestMapping("/get/{id}")
    Person show(@PathVariable("id") String id){
        return personList.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }

    @RequestMapping("/delete/{id}")
    Person delete(@PathVariable("id") String id){
        Person person =  personList.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
        personList.remove(person);
        return  person;
    }
}
