package com.example.demo.student;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public Student getStudent(int id) {
		return studentRepository.findStudentById(id)
				.orElseThrow(() -> new IllegalStateException("Student with " + id + " does not already!"));
	}

	public void addNewStudent(@NotNull Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("Email taken");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(int studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if (!exists) {
			throw new IllegalStateException("Student with " + studentId + " does not already!");
		}
		studentRepository.deleteStudentById(studentId);
	}

	@Transactional
	public void updateStudent(String name, String email, int id) {
		Student student = studentRepository.findStudentById(id)
				.orElseThrow(() -> new IllegalStateException("Student with " + id + " does not already!"));

		if (name != null && !name.isEmpty() && !name.equals(student.getName())) {
			student.setName(name);
		}

		if (email != null && !email.isEmpty() && !email.equals(student.getEmail())) {
			student.setEmail(email);
		}
	}
}
