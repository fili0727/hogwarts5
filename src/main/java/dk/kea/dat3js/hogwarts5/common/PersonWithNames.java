package dk.kea.dat3js.hogwarts5.common;

public interface PersonWithNames {
    String getFirstName();
    String getMiddleName();
    String getLastName();
    void setFirstName(String firstName);
    void setMiddleName(String middleName);
    void setLastName(String lastName);

    default String getFullName(){
        return getFirstName() + " " + ( getMiddleName() != null ? getMiddleName() + " " : "") + getLastName();
    }

    default void setFullName(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            return;
        }

        int firstSpace = fullName.indexOf(' ');
        int lastSpace = fullName.lastIndexOf(' ');

        if (firstSpace == -1) {
            // No spaces, implying only a first name is present.
            setFirstName(fullName);
            setMiddleName(null);
            setLastName(null);
        } else if (firstSpace == lastSpace) {
            // One space, implying only a first name and last name are present.
            setFirstName(fullName.substring(0, firstSpace));
            setMiddleName(null);
            setLastName(fullName.substring(firstSpace + 1));
        } else {
            // More than one space, implying a first name, middle name(s), and last name are present.
            setFirstName(fullName.substring(0, firstSpace));
            setMiddleName(fullName.substring(firstSpace + 1, lastSpace));
            setLastName(fullName.substring(lastSpace + 1));
        }
    }


    default String capitalize(String name){
        if (name == null) {
            return null;
        }
        if(name.length()==1) {
            return name.toUpperCase();
        }
        if(name.isBlank()) {
            return "";
        }
        if(name.contains(" ")) {
            int space = name.indexOf(' ');
            return capitalize(name.substring(0, space)) + " " + capitalize(name.substring(space+1));
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }


}
