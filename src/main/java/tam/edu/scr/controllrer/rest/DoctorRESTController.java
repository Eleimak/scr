package tam.edu.scr.controllrer.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tam.edu.scr.model.Doctor;
import tam.edu.scr.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorRESTController {
    private List<Doctor> doctorList = new ArrayList<>(
            Arrays.asList(
                    new Doctor("1","Aibolit", 23),
                    new Doctor("2","Watson", 21),
                    new Doctor("3","Strange", 25)
            )
    );

    @RequestMapping("/list")
    public List<Doctor> showAll(){
        return doctorList;
    }

    @RequestMapping("/get/{id}")
    Doctor show(@PathVariable("id") String id){
        return doctorList.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }

    @RequestMapping("/delete/{id}")
    Doctor delete(@PathVariable("id") String id){
        Doctor doctor =  doctorList.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
        doctorList.remove(doctor);
        return doctor;
    }
}
