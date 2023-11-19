package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonStudentFactory implements StudentFactory {
    /**
     * Requires: password is valid.
     * @param name
     * @param password
     * @return
     */

    @Override
    public Student create(String name, String password, String uoftEmail, String homeAddress) {
        return new Student(name, password,uoftEmail, homeAddress, false, , );
    }

}
