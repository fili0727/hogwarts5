package dk.kea.dat3js.hogwarts5.prefects;

import dk.kea.dat3js.hogwarts5.students.StudentResponseDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/prefects")
public class PrefectController {

    private final PrefectService prefectService;

    public PrefectController(PrefectService prefectService) {
        this.prefectService = prefectService;
    }

    @PostMapping("{id}")
    public ResponseEntity<StudentResponseDTO> makeStudentPrefect(@PathVariable Integer id) throws BadRequestException {
        return ResponseEntity.ok(prefectService.makeStudentPrefect(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentResponseDTO> getSinglePrefectByStudentId(@PathVariable Integer id) throws BadRequestException {
        try {
            return ResponseEntity.ok(prefectService.getSinglePrefectByStudentId(id));
        } catch (Exception e) {
            throw new BadRequestException("Student with id " + id + " is not a prefect");
        }

    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getPrefects() {
        return ResponseEntity.ok(prefectService.getPrefects());
    }

    @GetMapping("house/{houseName}")
    public ResponseEntity<List<StudentResponseDTO>> getPrefectsByHouseName(@PathVariable String houseName) {
        return ResponseEntity.ok(prefectService.getPrefectsByHouseName(houseName));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StudentResponseDTO> removeStudentPrefect(@PathVariable Integer id) {
        return ResponseEntity.ok(prefectService.removeStudentPrefect(id));
    }


}
