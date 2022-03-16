package com.study.springdatajpa.repository;

import com.study.springdatajpa.entity.Course;
import com.study.springdatajpa.entity.Material;
import com.study.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    TeacherRepository repository;

    @Test
    public void saveTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("Simone")
                .lastName("Jefferson")
                .build();

        repository.save(teacher);
    }
}