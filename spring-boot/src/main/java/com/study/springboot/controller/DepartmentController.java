package com.study.springboot.controller;

import com.study.springboot.entity.Department;
import com.study.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    DepartmentService service;

    @PostMapping("/departments")
    public Department post(@RequestBody Department department){
        return service.save(department);
    }

    @GetMapping("/departments")
    public List<Department> getAll(){
        return service.getAll();
    }

    @GetMapping("/departments/{id}")
    public Department getById(@PathVariable("id") Long id){
        return service.getById(id);
    }

    @DeleteMapping("/departments/{id}")
    public void deleteById(@PathVariable("id") Long id){
        service.deleteById(id);
    }
}
