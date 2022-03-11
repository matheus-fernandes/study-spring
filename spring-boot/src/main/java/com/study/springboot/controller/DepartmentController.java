package com.study.springboot.controller;

import com.study.springboot.entity.Department;
import com.study.springboot.service.DepartmentService;
import static com.study.springboot.message.DepartmentLoggingMessage.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.slf4j.LoggerFactory.*;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    private Logger logger = getLogger(Department.class);

    @PostMapping("/departments")
    public Department post(@RequestBody Department department){
        logger.info(INFO_POST_DEPARTMENT);

        return service.save(department);
    }

    @GetMapping("/departments")
    public List<Department> getAll(){
        logger.info(INFO_GET_ALL_DEPARTMENT);

        return service.getAll();
    }

    @GetMapping("/departments/{id}")
    public Department getById(@PathVariable("id") Long id){
        logger.info(INFO_GET_DEPARTMENT_BY_ID);

        return service.getById(id);
    }

    @DeleteMapping("/departments/{id}")
    public void deleteById(@PathVariable("id") Long id){
        logger.info(INFO_DELETE_DEPARTMENT);

        service.deleteById(id);
    }

    @PutMapping("/departments/{id}")
    public Department put(@PathVariable("id") Long id,
                          @RequestBody Department department){
        logger.info(INFO_PUT_DEPARTMENT);

        return service.putById(id, department);
    }

    @GetMapping("/departments/name/{name}")
    public Department getByName(@PathVariable("name") String name){
        logger.info(INFO_GET_DEPARTMENT_BY_NAME_IG_CASE);

        return service.getByName(name);
    }
}
