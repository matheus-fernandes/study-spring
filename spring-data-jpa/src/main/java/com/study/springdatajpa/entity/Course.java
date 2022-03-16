package com.study.springdatajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static com.study.springdatajpa.constants.CourseConstants.*;
import static javax.persistence.CascadeType.ALL;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = COURSE_TABLE
)
public class Course {
    @Id
    @SequenceGenerator(
            name = COURSE_SEQUENCE,
            sequenceName= COURSE_SEQUENCE_TABLE,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = COURSE_SEQUENCE
    )
    @Column(name = COURSE_ID)
    private Long id;

    @Column(name = COURSE_TITLE)
    private String title;

    @Column(name = COURSE_CREDIT)
    private Integer credit;

    @ManyToOne(
            cascade = ALL
    )
    @JoinColumn(
            name = COURSE_TEACHER_ID,
            referencedColumnName = Teacher.ID
    )
    private Teacher teacher;

    @OneToOne(
            cascade = ALL,
            mappedBy = "course"
    )
    Material material;

    @ManyToMany(
            cascade = ALL
    )
    @JoinTable(
            name = "student_course",
            joinColumns =  @JoinColumn(
                    name = COURSE_ID,
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name= "student_id",
                    referencedColumnName = "id"
            )
    )
    private List<Student> students;

    public void addStudent(Student student){
        if (students == null){
            students = new ArrayList<>();
        }

        students.add(student);
    }
}
