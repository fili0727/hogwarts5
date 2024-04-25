package dk.kea.dat3js.hogwarts5.teachers;

import dk.kea.dat3js.hogwarts5.house.House;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {
    @Test
    void getFullName() {
        // arrange
        Teacher teacher = new Teacher("Severus", "Prince", "Snape", slytherin, "Potions", LocalDate.of(1981, 11, 1));

        // act
        var fullName = teacher.getFullName();

        //assert
        assertEquals("Severus Prince Snape", fullName);
    }

    @Test
    void getFullNameWithoutMiddleName() {
        // arrange
        Teacher teacher = new Teacher("Severus", "Snape", slytherin, "Potions", LocalDate.of(1981, 11, 1));

        // act

        var fullName = teacher.getFullName();

        //assert
        assertEquals("Severus Snape", fullName);
    }

    @Test
    void setFullNameWithMiddleName() {
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", slytherin, "Potions", LocalDate.of(1981, 11, 1));

        // act
        teacher.setFullName("Severus Prince Snape");

        //assert
        assertEquals("Severus", teacher.getFirstName());
        assertEquals("Prince", teacher.getMiddleName());
        assertEquals("Snape", teacher.getLastName());

    }

    @Test
    void setFullNameWithMiddleNameAndMiddleNameIsNull() {
        // arrange
        Teacher teacher = new Teacher("Severus", null, null, slytherin, "Potions", LocalDate.of(1981, 11, 1));

        // act
        teacher.setFirstName("Severus");

        //assert
        assertEquals("Severus", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertNull(teacher.getLastName());
    }

    @Test
    void setFullNameWithoutMiddleName(){
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", slytherin, "Potions", LocalDate.of(1981, 11, 1));

        // act
        teacher.setFullName("Severus Snape");

        //assert
        assertEquals("Severus", teacher.getFirstName());
        assertNull( teacher.getMiddleName());
        assertEquals("Snape", teacher.getLastName());
    }

    @Test
    void setFullNameWithMultipleMiddleNames(){
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", slytherin, "Potions", LocalDate.of(1981, 11, 1));
        // act
        teacher.setFirstName("Severus");
        teacher.setMiddleName("Prince Sirius");
        teacher.setLastName("Snape");

        //assert
        assertEquals("Severus", teacher.getFirstName());
        assertEquals("Prince Sirius", teacher.getMiddleName());
        assertEquals("Snape", teacher.getLastName());
    }

    @Test
    void setFullNameWithEmptyString(){
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", slytherin, "Potions", LocalDate.of(1981, 11, 1));

        // act
        teacher.setFirstName("");
        teacher.setMiddleName("");
        teacher.setLastName("");

        //assert
        assertEquals("", teacher.getFirstName());
        assertEquals("", teacher.getMiddleName());
        assertEquals("", teacher.getLastName());
    }

    @Test
    void setFullNameWithFirstAndLastNameIsNull(){
        // arrange
        Teacher teacher = new Teacher(null, "middle", null, slytherin, "Potions", LocalDate.of(1981, 11, 1));

        // act
        teacher.setMiddleName("Prince");

        //assert
        assertNull(teacher.getFirstName());
        assertEquals("Prince", teacher.getMiddleName());
        assertNull(teacher.getLastName());
    }


    @Test
    void capitalizeIndividualNames(){
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", slytherin, "Potions", LocalDate.of(1981, 11, 1));

        // act
        teacher.setFirstName("severus");
        teacher.setMiddleName("prince");
        teacher.setLastName("snape");

        //assert
        assertEquals("Severus", teacher.getFirstName());
        assertEquals("Prince", teacher.getMiddleName());
        assertEquals("Snape", teacher.getLastName());
    }

    @Test
    void setFullNameWithNull(){
        // arrange
        Teacher teacher = new Teacher("First", "Middle", "Last", slytherin, "Potions", LocalDate.of(1981, 11, 1));
        // act
        teacher.setFullName(null);

        //assert
        assertEquals("First", teacher.getFirstName());
        assertEquals("Middle", teacher.getMiddleName());
        assertEquals("Last", teacher.getLastName());
    }

    @Test
    void capitalizeIndividualNamesWithCrazyCapitalization(){
        // arrange
        Teacher teacher = new Teacher("First", "Middle", "Last", slytherin, "Potions", LocalDate.of(1981, 11, 1));

        // act
        teacher.setFirstName("SeVeRuS");
        teacher.setMiddleName("pRiNcE");
        teacher.setLastName("sNaPe");

        //assert
        assertEquals("Severus", teacher.getFirstName());
        assertEquals("Prince", teacher.getMiddleName());
        assertEquals("Snape", teacher.getLastName());
    }

    private House gryffindor;
    private House slytherin;
    private House hufflepuff;
    private House ravenclaw;


}