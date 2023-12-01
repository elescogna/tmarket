package use_case.login;

import entities.Student;
import java.io.IOException;

public interface LoginDataAccessInterface {
    public boolean existsByEmail(String uoftEmail) throws IOException;
    public boolean checkPassword(String uoftEmail, String password)
            throws IOException;
    public Student getStudentByEmail(String email) throws IOException;
}
