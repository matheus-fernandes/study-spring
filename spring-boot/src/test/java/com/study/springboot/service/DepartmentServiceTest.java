package com.study.springboot.service;

import com.study.springboot.entity.Department;
import com.study.springboot.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    private DepartmentService service;

    @MockBean
    private DepartmentRepository repository;

    @BeforeEach
    void setUp() {
        Department itDepartment = Department.builder().id(1L)
                .name("IT").address("Bangalore").code("IT-06").build();

        Mockito.when(repository.findByNameIgnoreCase("IT"))
                .thenReturn(Optional.of(itDepartment));
    }

    @Test
    @DisplayName("find a department by a valid name")
    public void whenValidNameThenDepartmentShouldBeFound(){
        Optional<Department> department = repository.findByNameIgnoreCase("IT");
        assertTrue(department.isPresent());

        String name = department.map(Department::getName).orElse("");
        assertEquals(name, "IT");
    }

    @Test
    @DisplayName("Not Find a Department when Name is Invalid")
    public void whenNotValidNameThenDepartmentShouldNotBeFound(){
        Optional<Department> department = repository.findByNameIgnoreCase("çlkjsdfas");
        assertFalse(department.isPresent());
    }
}