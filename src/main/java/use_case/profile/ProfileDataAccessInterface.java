package use_case.profile;

import entities.Student;

import java.io.IOException;
import java.util.ArrayList;

public interface ProfileDataAccessInterface {

    Student getStudentByEmail(String id) throws IOException;
}
