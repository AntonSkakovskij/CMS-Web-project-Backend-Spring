package com.truba.web_server.Controller;
import com.truba.web_server.Entity.Student;
import com.truba.web_server.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Vector;

@CrossOrigin
@RestController
@RequestMapping
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity<?> postAddStudent(@Validated @RequestBody Student newStudent, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {

            System.out.println("[DENIED]\n" + newStudent.toString());
            List<FieldError> errors = bindingResult.getFieldErrors();
            Vector<String> fieldsWithErrors = new Vector<>();

            for (FieldError error : errors) {
                String fieldName = error.getField();
                fieldsWithErrors.add(fieldName);
                String errorMessage = error.getDefaultMessage();

                System.out.println("\t-Validation error in field '" + fieldName + "': " + errorMessage);
            }

            return new ResponseEntity<Vector>(fieldsWithErrors, HttpStatus.BAD_REQUEST);
        }
        else{
            studentService.saveStudent(newStudent);
            System.out.println("[ADDED]\n" + newStudent);
            return new ResponseEntity<Integer>(newStudent.getId(), HttpStatus.OK);
        }
    }

    @PutMapping("/editStudent")
    public ResponseEntity<?> postEditStudent(@RequestBody Student studentToEdit, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println("[DENIED]\n" + studentToEdit);
            List<FieldError> errors = bindingResult.getFieldErrors();
            Vector<String> fieldsWithErrors = new Vector<>();

            for (FieldError error : errors) {
                String fieldName = error.getField();
                fieldsWithErrors.add(fieldName);
                String errorMessage = error.getDefaultMessage();
                System.out.println("\t-Validation error in field '" + fieldName + "': " + errorMessage);
            }
            return new ResponseEntity<Vector>(fieldsWithErrors, HttpStatus.BAD_REQUEST);
        }
        else {
            studentService.updateStudent(studentToEdit);
            System.out.println("[EDITED]\n" + studentToEdit);
            return new ResponseEntity<String>("Successfully edited", HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{studentID}")
    public ResponseEntity<?> deleteStudentByID(@PathVariable int studentID){
        System.out.println("[DELETED]\n" + studentService.getStudentById(studentID).toString());
        studentService.deleteStudentById(studentID);
        return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
    }
}
