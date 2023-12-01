package use_case.searching;

import entities.Item;
import entities.Student;

public class SearchingInputData {
    private Student currentStudent;

    public SearchingInputData(Student currentStudent) {}

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }
    public Student getCurrentStudent() { return this.currentStudent; }
}
