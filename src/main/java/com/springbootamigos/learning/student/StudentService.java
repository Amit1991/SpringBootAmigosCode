package com.springbootamigos.learning.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    public List<Student> getSampleStudents() {

        List<Student> students = studentRepo.findAll();
        if (students.isEmpty())
        {
            return Student.sampleData;
        }
        return students;
    }

    public void addNew(Student student) throws Exception {

        List<Student> students = studentRepo.findAll();
        if(students.stream().filter(a -> a.getEmail().equalsIgnoreCase(student.getEmail())).count() == 0) {
            studentRepo.save(student);
        }
        else {
            throw new IllegalArgumentException("Email already registered");
        }
    }
}
