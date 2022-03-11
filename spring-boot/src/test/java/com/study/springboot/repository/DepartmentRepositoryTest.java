package com.study.springboot.repository;

import com.study.springboot.entity.Department;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void setUp(){
        Department engineering = Department.builder()
                .name("Electrical Engineering")
                .address("Campinas, SP - Brazil")
                .code("EE - 42").build();

        entityManager.persist(engineering);
    }

    @Test
    @Disabled
    @DisplayName("find a department by a valid id")
    public void whenValidIdThenDepartmentShouldBeFound(){
        Optional<Department> mayDepartment = repository.findById(1L);
        assertTrue(mayDepartment.isPresent());

        String code = mayDepartment.map(Department::getCode).orElse("");
        assertEquals(code, "EE - 42");
    }

    @Test
    @DisplayName("find a department by a valid name")
    public void whenValidNameThenDepartmentShouldBeFound(){
        Optional<Department> mayDepartment = repository
                .findByNameIgnoreCase("ElectricAl EnginEering");

        assertTrue(mayDepartment.isPresent());

        String code = mayDepartment.map(Department::getCode).orElse("");
        assertEquals(code, "EE - 42");
    }

    @Test
    @DisplayName("not find a department by a invalid name")
    public void whenInvalidNameThenDepartmentShouldNotBeFound(){
        Optional<Department> mayDepartment = repository
                .findByNameIgnoreCase("ElectricAl EnginEerinoo");

        assertFalse(mayDepartment.isPresent());
    }

}