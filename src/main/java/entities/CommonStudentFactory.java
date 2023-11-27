package entities;

public class CommonStudentFactory implements StudentFactory {
    /**
     * Requires: password is valid.
     * @param name
     * @param password
     * @return
     */

    @Override
    public Student create(String name, String password, String uoftEmail, String homeAddress) {
        return new Student(name, password, homeAddress, uoftEmail);
    }

}
