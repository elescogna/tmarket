package use_case.login;

import entities.Student;

public class LoginOutputData {
    private Student student;

    /**
     * Creates a new LoginInputData with the given parameters.
     *
     * @param student the student with which to create the LoginOutputData
     */
    public LoginOutputData(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
}
