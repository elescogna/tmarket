package use_case.posting;

import entities.Student;

public class PostingOutputData {
    Student currentStudent = null;

    public PostingOutputData(Student currentStudent) {
        this.currentStudent = currentStudent;
    }

    public Student getCurrentStudent() { return currentStudent; }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }
}
