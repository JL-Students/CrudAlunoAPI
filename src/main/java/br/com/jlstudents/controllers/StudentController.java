package br.com.jlstudents.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jlstudents.entities.Student;
import br.com.jlstudents.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    private StudentService studentsService;

    @GetMapping
    @Operation(summary = "Obtém todos os estudantes cadastrados no sistema.")
    public ResponseEntity<List<Student>> findAllStudents() {
        List<Student> students = studentsService.findAll();
        return ResponseEntity.ok().body(students);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Obtém um estudante pelo seu Id cadastrado na base de dados do sistema.")
    public ResponseEntity<Student> findStudentById(@PathVariable Integer id) {
    	Student student = studentsService.findById(id);
        return ResponseEntity.ok().body(student);
    }
    
    @PostMapping
    @Operation(summary = "Realiza o cadastro de um novo estudante no sistema.")
    public ResponseEntity<Student> create(@RequestBody Student newStudent) {
    	Student student = studentsService.insert(newStudent);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(student.getId()).toUri();
        return ResponseEntity.created(uri).body(student);
    }
    
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Realiza a exclusão de um estudante do sistema pelo seu Id.")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    	studentsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza dados do cadastro de um estudante já existente na base de dados do sistema.")
    public ResponseEntity<Student> update(@RequestBody Student studentToUpdate, @PathVariable Integer id) {
    	Student student = studentsService.update(id, studentToUpdate); 
        return ResponseEntity.ok().body(student);
    }

}
