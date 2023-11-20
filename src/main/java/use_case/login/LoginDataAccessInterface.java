package use_case.login;

import entities.Student;

import java.io.IOException;

public interface LoginDataAccessInterface {
    public boolean existsByEmail(String username);
    public boolean existsByPassword(String password);
    public Student getStudentByEmail(String email) throws IOException;
}
