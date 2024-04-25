package dk.kea.dat3js.hogwarts5.prefects;


import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.students.StudentRepository;
import dk.kea.dat3js.hogwarts5.students.StudentResponseDTO;
import dk.kea.dat3js.hogwarts5.students.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrefectService {
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public PrefectService(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }


    public StudentResponseDTO makeStudentPrefect(Integer id) throws BadRequestException {
        Student studentInDb = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found"));

        List<Student> prefectsInHouse = studentRepository.findAllByHouseNameAndPrefectIsTrue(studentInDb.getHouse().getName());

        if (studentInDb.getSchoolYear() < 5)
            throw new BadRequestException("Student must be year 5 or above to become a prefect");

        if (prefectsInHouse.size() >= 2)
            throw new BadRequestException("There already exists two prefects in the house");

        for (Student prefectStudent : prefectsInHouse) {
            if (prefectStudent.isMale() == studentInDb.isMale())
                throw new BadRequestException("There already exists a" + studentInDb.isMale() + " prefect student of that gender in this house");
        }

        studentInDb.setPrefect(true);
        studentRepository.save(studentInDb);
        return studentService.toDTO(studentInDb);
    }

    public StudentResponseDTO getSinglePrefectByStudentId(Integer id) {
        Student studentInDb = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found"));

        if (!studentInDb.isPrefect())
            throw new EntityNotFoundException("Student with id " + id + " is not a prefect");

        return studentService.toDTO(studentInDb);
    }

    public List<StudentResponseDTO> getPrefects() {
        return studentRepository.findAllByPrefectIsTrue().stream().map(studentService::toDTO).toList();
    }

    public List<StudentResponseDTO> getPrefectsByHouseName(String houseName) {
        return studentRepository.findAllByHouseNameAndPrefectIsTrue(houseName).stream().map(studentService::toDTO).toList();
    }

    public StudentResponseDTO removeStudentPrefect(Integer id) {
        Student studentInDb = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found"));

        studentInDb.setPrefect(false);

        studentRepository.save(studentInDb);
        return studentService.toDTO(studentInDb);
    }

}
