package use_case.profile;

import entities.Student;

public class ProfileInputData{
    final private Student student;

    public ProfileInputData(Student student){
        this.student = student;
    }

    public Student getStudent(){ return this.student; }
}
