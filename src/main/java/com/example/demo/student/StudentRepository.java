package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    //@Query(value = "SELECT * FROM public.student WHERE email = ?", nativeQuery = true)
    Optional<Student> findStudentByEmail(String email);

    Optional<Student> findStudentById(int id);

    @Transactional
    void deleteStudentById(int id);
}
