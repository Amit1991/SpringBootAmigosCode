package com.springbootamigos.learning.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getSampleStudents() {

        return studentService.getSampleStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) throws Exception {
        studentService.addNew(student);
    }
}
