package com.springbootamigos.learning.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @DeleteMapping("{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }

    @PutMapping("{id}")
    public void updateStudentById(@PathVariable Long id,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) String email,
                                  @RequestParam(required = false) String dob) throws Exception {

        studentService.updateStudent(id, name, email, dob);
    }
}
