package entities;

public class CommonStudentFactory implements StudentFactory {
    /**
     * Requires: password is valid. Creates a new student with the given parameters.
     *
     * @param name the name of the new student
     * @param password the password of the new student
     * @param homeAddress the home address of the new student
     * @param uoftEmail the uoft email of the new student
     * @return the new student with all of the above attributes
     */
    @Override
    public Student create(String name, String password, String homeAddress, String uoftEmail) {
        return new Student(name, password, homeAddress, uoftEmail);
    }

}
