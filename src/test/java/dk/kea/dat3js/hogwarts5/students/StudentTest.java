package dk.kea.dat3js.hogwarts5.students;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getFullName() {
        // arrange
        Student student = new Student("Harry", "James", "Potter", null, 1);

        // act
        var fullName = student.getFullName();

        //assert
        assertEquals("Harry James Potter", fullName);
    }

    @Test
    void getFullNameWithoutMiddleName() {
        // arrange
        Student student = new Student("Harry","Potter", null, 1);

        // act
        var fullName = student.getFullName();

        //assert
        assertEquals("Harry Potter", fullName);
    }

    @Test
    void setFullNameWithMiddleName() {
        // arrange
        Student student = new Student("first", "middle", "last", null, 1);

        // act
        student.setFullName("Harry James Potter");

        //assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("James", student.getMiddleName());
        assertEquals("Potter", student.getLastName());

    }

    @Test
    void setFullNameWithMiddleNameAndMiddleNameIsNull() {
        // arrange
        Student student = new Student("first", null, null, null, 1);

        // act
        student.setFirstName("Harry");

        //assert
        assertEquals("Harry", student.getFirstName());
        assertNull(student.getMiddleName());
        assertNull(student.getLastName());
    }

    @Test
    void setFullNameWithoutMiddleName(){
        // arrange
        Student student = new Student("first", "middle", "last", null, 1);

        // act
        student.setFullName("Harry Potter");

        //assert
        assertEquals("Harry", student.getFirstName());
        assertNull( student.getMiddleName());
        assertEquals("Potter", student.getLastName());
    }

    @Test
    void setFullNameWithMultipleMiddleNames(){
        // arrange
        Student student = new Student("first", "middle", "last", null, 1);

        // act
        student.setFirstName("Harry");
        student.setMiddleName("James Sirius");
        student.setLastName("Potter");

        //assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("James Sirius", student.getMiddleName());
        assertEquals("Potter", student.getLastName());
    }

    @Test
    void setFullNameWithEmptyString(){
        // arrange
        Student student = new Student("first", "middle", "last", null, 1);

        // act
        student.setFirstName("");
        student.setMiddleName("");
        student.setLastName("");

        //assert
        assertEquals("", student.getFirstName());
        assertEquals("", student.getMiddleName());
        assertEquals("", student.getLastName());
    }

    @Test
    void setFullNameWithFirstAndLastNameIsNull(){
        // arrange
        Student student = new Student(null, "middle", null, null, 1);

        // act
        student.setMiddleName("James");

        //assert
        assertNull(student.getFirstName());
        assertEquals("James", student.getMiddleName());
        assertNull(student.getLastName());
    }


    @Test
    void capitalizeIndividualNames(){
        // arrange
        Student student = new Student("first", "middle", "last", null, 1);

        // act
        student.setFirstName("harry");
        student.setMiddleName("james");
        student.setLastName("potter");

        //assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("James", student.getMiddleName());
        assertEquals("Potter", student.getLastName());
    }

    @Test
    void setFullNameWithNull(){
        // arrange
        Student student = new Student("First", "Middle", "Last", null, 1);

        // act
        student.setFullName(null);

        //assert
        assertEquals("First", student.getFirstName());
        assertEquals("Middle", student.getMiddleName());
        assertEquals("Last", student.getLastName());
    }

    @Test
    void capitalizeIndividualNamesWithCrazyCapitalization(){
        // arrange
        Student student = new Student("first", "middle", "last", null, 1);

        // act
        student.setFirstName("hArRy");
        student.setMiddleName("jAmEs");
        student.setLastName("pOtTeR");

        //assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("James", student.getMiddleName());
        assertEquals("Potter", student.getLastName());
    }
}