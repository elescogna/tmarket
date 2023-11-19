package use_case.profile;

import entities.Student;

import java.io.IOException;
import java.util.ArrayList;

public interface ProfileDataAccessInterface {
    ArrayList<Student> getAllStudents() throws IOException;
    Student getStudentById(String studentId) throws IOException;
}
