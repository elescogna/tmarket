package use_case.posting;

import entities.Student;

public class PostingInputData {
    Student currentStudent = null;

    public PostingInputData(Student currentStudent) {
        this.currentStudent = currentStudent;
    }

    public Student getCurrentStudent() { return currentStudent; }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }
}
