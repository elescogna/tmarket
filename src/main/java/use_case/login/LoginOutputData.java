package use_case.login;

import entities.Student;

public class LoginOutputData {
    private Student student;

    public LoginOutputData(Student student) { this.student = student; }

    public Student getStudent() { return student; }
}
