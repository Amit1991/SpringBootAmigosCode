package com.springbootamigos.learning.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.time.LocalDate;
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

    public void deleteStudentById(Long id) {

        if(studentRepo.existsById(id))
        {
            studentRepo.deleteById(id);
        }
        else throw new IllegalArgumentException("Student with id " + id + " does not exist");
    }


    @Transactional
    public void updateStudent(Long id, String name, String email, String dob) throws Exception {

        if(studentRepo.existsById(id))
        {
            Student student = studentRepo.findById(id).get();
            if(!name.isEmpty()) student.setName(name);
            if(!email.isEmpty()) student.setEmail(email);
            if(!dob.isEmpty())
            {
                try {
                    LocalDate ld = LocalDate.parse(dob);
                    student.setDob(ld);
                }
                catch (Exception ex)
                {
                    throw new ParseException("DOB should be in yyyy-mm-dd format.", 0);
                }
            }
        }
        else throw new IllegalArgumentException("Student with id '" + id + "' does not exist.");
    }
}
