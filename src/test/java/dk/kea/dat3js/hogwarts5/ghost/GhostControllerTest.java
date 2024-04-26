package dk.kea.dat3js.hogwarts5.ghost;

import dk.kea.dat3js.hogwarts5.house.House;
import dk.kea.dat3js.hogwarts5.house.HouseRepository;
import dk.kea.dat3js.hogwarts5.house.HouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GhostController.class)
@ComponentScan(basePackageClasses = {HouseService.class})
class GhostControllerTest {

    @MockBean
    private HouseRepository houseRepository;

    @Autowired
    private MockMvc mockMvc;



    @Test
    void getAllGhosts() throws Exception {
        when(houseRepository.findById("Gryffindor")).thenReturn(Optional.of(new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"})));
        when(houseRepository.findById("Ravenclaw")).thenReturn(Optional.of(new House("Ravenclaw", "Rowena Ravenclaw", new String[]{"blue", "bronze"})));
        when(houseRepository.findById("Hufflepuff")).thenReturn(Optional.of(new House("Hufflepuff", "Helga Hufflepuff", new String[]{"yellow", "black"})));
        when(houseRepository.findById("Slytherin")).thenReturn(Optional.of(new House("Slytherin", "Salazar Slytherin", new String[]{"green", "silver"})));
        mockMvc.perform(get("/ghosts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[*].name").value(containsInAnyOrder("Nearly Headless Nick", "The Grey Lady", "The Fat Friar", "The Bloody Baron")));

    }


    @Test
    void getGhostByName() throws Exception {
        when(houseRepository.findById("Gryffindor")).thenReturn(Optional.of(new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"})));
        when(houseRepository.findById("Ravenclaw")).thenReturn(Optional.of(new House("Ravenclaw", "Rowena Ravenclaw", new String[]{"blue", "bronze"})));
        when(houseRepository.findById("Hufflepuff")).thenReturn(Optional.of(new House("Hufflepuff", "Helga Hufflepuff", new String[]{"yellow", "black"})));
        when(houseRepository.findById("Slytherin")).thenReturn(Optional.of(new House("Slytherin", "Salazar Slytherin", new String[]{"green", "silver"})));
        mockMvc.perform(get("/ghosts/Nearly Headless Nick"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nearly Headless Nick"))
                .andExpect(jsonPath("$.realName").value("Sir Nicholas de Mimsy-Porpington"))
                .andExpect(jsonPath("$.house.name").value("Gryffindor"));

    }
}