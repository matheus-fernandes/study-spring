package com.study.springdatajpa.repository;

import com.study.springdatajpa.constants.CourseConstants;
import com.study.springdatajpa.entity.Course;
import com.study.springdatajpa.entity.Material;
import com.study.springdatajpa.entity.Student;
import com.study.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.study.springdatajpa.constants.CourseConstants.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    CourseRepository repository;

    @Test
    public void printAllCourses(){
        List<Course> course = repository.findAll();

        System.out.println(course);
    }

    @Test
    public void saveCourseWithTeacherAndMaterial() {
        Teacher teacher =
                Teacher.builder()
                    .firstName("Anna")
                    .lastName("Jackson")
                    .build();

        Course course =
                Course.builder()
                    .title("ReactJS")
                    .credit(6)
                    .teacher(teacher)
                    .build();

        Material material =
                Material.builder()
                    .url("www.reactjs.com")
                    .course(course)
                    .build();

        course.setMaterial(material);
        repository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3);

        Pageable secondPageWithTwoRecords =
                PageRequest.of(1, 2);

        Page<Course> courses1 = repository
                .findAll(firstPageWithThreeRecords);

        System.out.println("courses: " + courses1.getContent());
        System.out.println("total pages: " + courses1.getTotalPages());
        System.out.println("total courses: " + courses1.getTotalElements());

        Page<Course> courses2 = repository
                .findAll(secondPageWithTwoRecords);

        System.out.println("courses: " + courses2.getContent());
        System.out.println("total pages: " + courses2.getTotalPages());
        System.out.println("total courses: " + courses2.getTotalElements());
    }

    @Test
    public void findAllSorting(){
        Pageable sortByTitle =
                PageRequest.of(
                        0,
                        2,
                        Sort.by(COURSE_TITLE)
                );

        Pageable sortByCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by(COURSE_CREDIT).descending()
                );

        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by(COURSE_TITLE).descending().and(
                        Sort.by(COURSE_CREDIT).ascending())
                );

        Page<Course> courses1 = repository
                .findAll(sortByTitle);

        Page<Course> courses2 = repository
                .findAll(sortByCreditDesc);

        Page<Course> courses3 = repository
                .findAll(sortByTitleAndCreditDesc);

        System.out.println(courses1);
        System.out.println(courses2);
        System.out.println(courses3);
    }

    @Test
    public void printFindByTitleContaining(){
        Pageable firstPageTenRecords =
                PageRequest.of(0, 10);

        Page courses = repository
                .findByTitleContaining("D", firstPageTenRecords);

        System.out.println(courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher =
                Teacher.builder()
                        .firstName("Jackson")
                        .lastName("James")
                        .build();

        Student student =
                Student.builder()
                        .email("bia@mail.com")
                        .firstName("Bia")
                        .lastName("Vit")
                        .build();

        Course course =
                Course.builder()
                        .title("AI")
                        .credit(12)
                        .teacher(teacher)
                        .students(List.of(student))
                        .build();

        repository.save(course);
    }
}