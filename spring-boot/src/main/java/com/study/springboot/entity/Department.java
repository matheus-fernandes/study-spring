package com.study.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static com.study.springboot.message.DepartmentValidationMessage.*;
import static javax.persistence.GenerationType.*;



@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column
    @NotBlank(message=NAME_NOT_BLANK)
    private String name;

    @Column
    private String address;

    @Column
    private String code;
}
