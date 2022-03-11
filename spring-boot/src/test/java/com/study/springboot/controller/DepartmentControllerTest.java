package com.study.springboot.controller;

import com.study.springboot.entity.Department;
import com.study.springboot.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService service;

    private Department newDepartment;
    private Department savedDepartment;

    @BeforeEach
    public void setUp(){
        Department.DepartmentBuilder builder = Department.builder()
                .name("ADM").code("ADM-06").address("Bangalore");

        newDepartment = builder.build();
        savedDepartment = builder.id(1L).build();
    }

    @Test
    public void postTest() throws Exception {
        when(service.save(newDepartment)).thenReturn(savedDepartment);

        mockMvc.perform(post("/departments")
                .contentType(APPLICATION_JSON)
                .content("{\n" +
                    "    \"name\" : \"ADM\",\n" +
                    "    \"address\" : \"Bangalore\",\n" +
                    "    \"code\": \"ADM-06\"\n" +
                "}")
        ).andExpect(status().isOk());
    }

    @Test
    public void getByIdTest() throws Exception {
        when(service.getById(1L)).thenReturn(savedDepartment);

        mockMvc.perform(
                get("/departments/1")
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$.name").value(savedDepartment.getName())
        );
    }

    /*
    public void getAllTest(){}
    public void deleteByIdTest(){}
    public void putTest(){}
    public void getByNameTest(){}
    */
}