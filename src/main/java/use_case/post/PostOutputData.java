package use_case.post;

import entities.Student;

public class PostOutputData {
    final Student student;

    /**
     * Creates a new PostOutputData with the parameters given.
     *
     * @param student the student with which to create the PostOutputData
     */
    public PostOutputData(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
}
