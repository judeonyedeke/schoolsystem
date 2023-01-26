package com.example.schoolsystem.student.Service;

import com.example.schoolsystem.student.DTOs.request.CreateStudentRequest;
import com.example.schoolsystem.student.Data.Repositories.StudentRepository;
import com.example.schoolsystem.student.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class  StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents(){
       return studentRepository.findAll();
    }

    public void addNewStudents(Student student) {
        Optional<Student> studentByEmail = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()){
            throw new IllegalStateException("Email already exists.");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException(
                    "student with id " + studentId + " does not exist."
            );
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId, String nameUpdate, String emailUpdate) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id" + studentId + "does not exist"));

        if (nameUpdate != null && nameUpdate.length() > 0 && !Objects.equals(student.getName(), nameUpdate)) {
            student.setName(nameUpdate);
        }

            if (emailUpdate != null && emailUpdate.length() > 0 && !Objects.equals(student.getEmail(), emailUpdate)){
                Optional<Student> studentOptional = studentRepository.findStudentByEmail(emailUpdate);
                if (studentOptional.isPresent()) {
                    throw new IllegalStateException("email already used");
                }
                student.setEmail(emailUpdate);
            }
        }
    }

