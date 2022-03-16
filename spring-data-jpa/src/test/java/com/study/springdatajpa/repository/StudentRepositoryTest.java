package com.study.springdatajpa.repository;

import com.study.springdatajpa.entity.Guardian;
import com.study.springdatajpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .email("matheus@email.com")
                .firstName("Matheus")
                .lastName("Silva")
                .build();

        repository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Jane")
                .email("jane@email")
                .mobile("999999999")
                .build();

        Student student = Student.builder()
                .email("joao@email.com")
                .firstName("João")
                .lastName("Carlos")
                .guardian(guardian)
                .build();

        repository.save(student);
    }

    @Test
    public void printStudentsByFirstName(){
        List<Student> students =repository.findByFirstName("Matheus");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentsByFirstNameContaining(){
        List<Student> students = repository.findByFirstNameContaining("Mat");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentsBasedOnGuardianName(){
        List<Student> students = repository.findByGuardianName("Jane");
        System.out.println(students);
    }

    @Test
    public void printStudentsLastNameNotNull(){
        List<Student> students =
                repository.findByLastNameNotNull();
        System.out.println(students);
    }

    @Test
    public void printAllStudent(){
        List<Student> students = repository.findAll();
        System.out.println(students);
    }

    @Test
    public void printStudentByLastNameAndFirstName(){
        Student student = repository.findByFirstNameAndLastName("João", "Carlos");
        System.out.println(student);
    }

    @Test
    public void printGetStudentByEmail(){
        Student student = repository.getStudentByEmail("matheus@email.com");
        System.out.println(student);
    }

    @Test
    public void printGetFirstNameByEmail(){
        String name = repository.getFirstNameByEmail("matheus@email.com");
        System.out.println(name);
    }

    @Test
    public void printGetStudentByEmailNative(){
        Student student = repository.getStudentByEmailNative("matheus@email.com");
        System.out.println(student);
    }

    @Test
    public void printGetStudentByEmailNativeParam(){
        Student student = repository.getStudentByEmailNativeParam("matheus@email.com");
        System.out.println(student);
    }

    @Test
    public void updateStudentNameByEmailId(){
        repository.updateStudentNameByEmailId("Bob", "joao@email.com");
    }
}