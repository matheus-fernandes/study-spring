package com.study.springdatajpa.repository;

import com.study.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    Student findByFirstNameAndLastName(String firstName, String lastName);

    @Query("select s from Student s where s.email = ?1")
    Student getStudentByEmail(String email);

    @Query("select s.firstName from Student s where s.email = ?1")
    String getFirstNameByEmail(String email);

    @Query(
            value = "SELECT * FROM student s WHERE s.email = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailNative(String email);

    @Query(
            value = "SELECT * FROM student s WHERE s.email = :email",
            nativeQuery = true
    )
    Student getStudentByEmailNativeParam(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(
            value="UPDATE student SET first_name = ?1 WHERE email = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String email);
}
