package com.truba.web_server.service;


import com.truba.web_server.Entity.Student;
import com.truba.web_server.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public String deleteStudentById(int id) {
        studentRepository.deleteById(id);
        return "Student deleted: " + id;
    }

    public  Student updateStudent(Student student){
        Student existingStudent = studentRepository.findById(student.getId()).orElse(null);
        existingStudent.setGroupName(student.getGroupName());
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setGender(student.getGender());
        existingStudent.setBirthday(student.getBirthday());

        return saveStudent(existingStudent);
    }

    /*public List<Student> saveStudents(List<Student> students) {
        return studentRepository.saveAll(students);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public String deleteAllStudents() {
        studentRepository.deleteAll();
        return "All students deleted";
    }*/
}
