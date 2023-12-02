package use_case.searching;

import entities.Item;
import entities.Student;

public class SearchingOutputData {
    final Student student;
    public SearchingOutputData(Student student) {
        this.student = student;
    }
    public Student getStudent() {
        return student;
    }
}
