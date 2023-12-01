package use_case.post;

import entities.Student;

public class PostOutputData {
    final Student student;
    public PostOutputData(Student student) { this.student = student; }
    public Student getStudent() { return student; }
}
