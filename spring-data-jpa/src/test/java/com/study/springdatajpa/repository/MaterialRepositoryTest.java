package com.study.springdatajpa.repository;

import com.study.springdatajpa.entity.Course;
import com.study.springdatajpa.entity.Material;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MaterialRepositoryTest {

    @Autowired
    private MaterialRepository repository;

    @Test
    public void saveMaterial(){
        Course course = Course.builder()
                .title("DSA")
                .credit(6)
                .build();

        Material material = Material.builder()
                .url("www.google.com")
                .course(course)
                .build();

        repository.save(material);
    }

    @Test
    public void printAllMaterials(){
        List<Material> materials =
                repository.findAll();

        System.out.println(materials);
    }

}