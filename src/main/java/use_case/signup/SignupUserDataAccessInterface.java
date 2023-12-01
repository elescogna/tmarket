package use_case.signup;

import entities.Student;
import java.io.IOException;

public interface SignupUserDataAccessInterface {
    boolean existsByEmail(String uoftEmail) throws IOException;

    void addStudent(Student user) throws IOException;
}
