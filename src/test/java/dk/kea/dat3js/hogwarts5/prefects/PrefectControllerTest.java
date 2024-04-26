package dk.kea.dat3js.hogwarts5.prefects;


import dk.kea.dat3js.hogwarts5.house.House;
import dk.kea.dat3js.hogwarts5.house.HouseRepository;
import dk.kea.dat3js.hogwarts5.house.HouseService;
import dk.kea.dat3js.hogwarts5.students.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PrefectController.class)
@ComponentScan(basePackageClasses = {HouseService.class})
public class PrefectControllerTest {

    @MockBean
    private HouseRepository houseRepository;
    @MockBean
    private PrefectService prefectService;
    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllPrefects() throws Exception {
        when(houseRepository.findById("Gryffindor")).thenReturn(Optional.of(new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"})));
        when(houseRepository.findById("Ravenclaw")).thenReturn(Optional.of(new House("Ravenclaw", "Rowena Ravenclaw", new String[]{"blue", "bronze"})));
        when(houseRepository.findById("Hufflepuff")).thenReturn(Optional.of(new House("Hufflepuff", "Helga Hufflepuff", new String[]{"yellow", "black"})));
        when(houseRepository.findById("Slytherin")).thenReturn(Optional.of(new House("Slytherin", "Salazar Slytherin", new String[]{"green", "silver"})));
        mockMvc.perform(get("/prefects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[*].firstName").value(containsInAnyOrder("George", "Percy", "Hermione")));


    }

    @Test
    void makeStudentPrefect() throws Exception {
        when(houseRepository.findById("Gryffindor")).thenReturn(Optional.of(new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"})));
        when(houseRepository.findById("Ravenclaw")).thenReturn(Optional.of(new House("Ravenclaw", "Rowena Ravenclaw", new String[]{"blue", "bronze"})));
        when(houseRepository.findById("Hufflepuff")).thenReturn(Optional.of(new House("Hufflepuff", "Helga Hufflepuff", new String[]{"yellow", "black"})));
        when(houseRepository.findById("Slytherin")).thenReturn(Optional.of(new House("Slytherin", "Salazar Slytherin", new String[]{"green", "silver"})));
        mockMvc.perform(post("/prefects/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.student.firstName").value("Hermione"))
                  .andExpect(jsonPath("$.student.isPrefect").value(true));

    }

    @Test
    void makeStudentPrefectBadRequest() throws Exception {
        when(houseRepository.findById("Gryffindor")).thenReturn(Optional.of(new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"})));
        when(houseRepository.findById("Ravenclaw")).thenReturn(Optional.of(new House("Ravenclaw", "Rowena Ravenclaw", new String[]{"blue", "bronze"})));
        when(houseRepository.findById("Hufflepuff")).thenReturn(Optional.of(new House("Hufflepuff", "Helga Hufflepuff", new String[]{"yellow", "black"})));
        when(houseRepository.findById("Slytherin")).thenReturn(Optional.of(new House("Slytherin", "Salazar Slytherin", new String[]{"green", "silver"})));
        mockMvc.perform(post("/prefects/1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.firstName").value("George"))
                .andExpect(jsonPath("$.isPrefect").value(false));
    }
    

}

