package use_case.signup;

import entities.Student;

import java.io.IOException;

public interface SignupUserDataAccessInterface {
    boolean existsByEmail(String uoftEmail);

    void addStudent(Student user) throws IOException;
}
