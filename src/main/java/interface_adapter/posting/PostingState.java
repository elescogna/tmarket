package interface_adapter.posting;
import entities.Student;

import entities.Item;
import entities.Order;

import java.util.ArrayList;

public class PostingState {
    private Student student;
    private String id;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = student.getId();
    }

}
