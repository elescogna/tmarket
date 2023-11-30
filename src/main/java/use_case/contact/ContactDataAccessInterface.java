package use_case.contact;

import entities.Student;
import java.io.IOException;

public interface ContactDataAccessInterface {
    public Student getStudentById(String id) throws IOException;
}
