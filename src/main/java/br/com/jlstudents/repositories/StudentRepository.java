package br.com.jlstudents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jlstudents.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    
}
