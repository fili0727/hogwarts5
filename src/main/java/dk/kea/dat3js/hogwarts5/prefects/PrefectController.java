package dk.kea.dat3js.hogwarts5.prefects;

import dk.kea.dat3js.hogwarts5.house.HouseService;
import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.students.StudentResponseDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/prefects")
public class PrefectController {
    private final HouseService houseService;
    private final PrefectService prefectService;
    private List<Student> studentsTest = new ArrayList<>();

    public PrefectController(PrefectService prefectService, HouseService houseService) {
        this.prefectService = prefectService;
        this.houseService = houseService;
    }

    private void initPrefects() {
        if (studentsTest.isEmpty()) {
            studentsTest.add(new Student(1,"George", "Fabian", "Weasley", houseService.findById("Gryffindor").get(), 5, false, true));
            studentsTest.add(new Student(2,"Percy", "Ignatius", "Weasley", houseService.findById("Gryffindor").get(), 5, true, true));
            studentsTest.add( new Student(3,"Hermione", "Jean", "Granger", houseService.findById("Gryffindor").get(), 5, false, false));
        }
    }


    @PostMapping("{id}")
    public ResponseEntity<StudentResponseDTO> makeStudentPrefect(@PathVariable Integer id) throws BadRequestException {
       initPrefects();
        return ResponseEntity.ok(prefectService.makeStudentPrefect(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentResponseDTO> getSinglePrefectByStudentId(@PathVariable Integer id) throws BadRequestException {
        try {
           initPrefects();
            return ResponseEntity.ok(prefectService.getSinglePrefectByStudentId(id));
        } catch (Exception e) {
            throw new BadRequestException("Student with id " + id + " is not a prefect");
        }

    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getPrefects() {
        initPrefects();
        return ResponseEntity.ok(prefectService.getPrefects());
    }

    @GetMapping("house/{houseName}")
    public ResponseEntity<List<StudentResponseDTO>> getPrefectsByHouseName(@PathVariable String houseName) {
        initPrefects();
        return ResponseEntity.ok(prefectService.getPrefectsByHouseName(houseName));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StudentResponseDTO> removeStudentPrefect(@PathVariable Integer id) {
        initPrefects();
        return ResponseEntity.ok(prefectService.removeStudentPrefect(id));
    }


}
