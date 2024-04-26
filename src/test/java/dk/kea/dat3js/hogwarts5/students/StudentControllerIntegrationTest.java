package dk.kea.dat3js.hogwarts5.students;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class StudentControllerIntegrationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void notNull(){
        assertNotNull(webClient);
    }

    @Test
    void createStudentWithFullName() {
        webClient.post().uri("/students").contentType(MediaType.APPLICATION_JSON).bodyValue("""
                        {
                        "fullName": "Daniel Blip Blop",
                        "house": "Gryffindor",
                        "schoolYear": 1
                        }""")
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isNumber()
                .jsonPath("$.firstName").isEqualTo("Daniel")
                .jsonPath("$.middleName").isEqualTo("Blip")
                .jsonPath("$.lastName").isEqualTo("Blop")
                .jsonPath("$.fullName").isEqualTo("Daniel Blip Blop")
                .jsonPath("$.house").isEqualTo("Gryffindor")
                .jsonPath("$.schoolYear").isEqualTo(1);

    }

    @Test
    void createStudentWithNameParts() {
        webClient
                .post().uri("/students")
                .contentType(MediaType.APPLICATION_JSON).bodyValue("""
                            {
                            "firstName": "Daniel",
                            "middleName": "Blip",
                            "lastName": "Blop",
                            "house": "Gryffindor",
                            "schoolYear": 1
                            }
                            """)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().json("""
                            {
                            "firstName": "Daniel",
                            "middleName": "Blip",
                            "lastName": "Blop",
                            "fullName": "Daniel Blip Blop",
                            "house": "Gryffindor",
                            "schoolYear": 1
                            }
                        """)
                .jsonPath("$.id").exists();
    }

    }

