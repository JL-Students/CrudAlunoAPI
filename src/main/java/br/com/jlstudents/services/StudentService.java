package br.com.jlstudents.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.jlstudents.entities.Student;
import br.com.jlstudents.repositories.StudentRepository;
import br.com.jlstudents.services.exceptions.DatabaseException;
import br.com.jlstudents.services.exceptions.EntityCrudStudentAPINotFoundException;
import br.com.jlstudents.services.exceptions.GenericCrudAPIStudentsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> findAll() {
		List<Student> students = studentRepository.findAll();
		if (students == null || students.isEmpty()) {
			throw new EntityCrudStudentAPINotFoundException("Nenhum estudante foi encontrado na base de dados do sistema.");
		}
		return studentRepository.findAll();
	}
	
	public Student findById(Integer id) {
		Optional<Student> student = studentRepository.findById(id);		
		return student.orElseThrow(() -> new EntityCrudStudentAPINotFoundException("Estudante não se encontra cadastrado na base de dados do sistema.", id));
	}
	
	public Student insert(Student student) {
		try {
			validateStudent(student);
			return studentRepository.save(student);
		} catch (Exception e) {
			throw new DatabaseException("Erro ao cadastrar estudante na base de dados. " + e.getMessage());
		}
	}
	
	private void validateStudent(Student student) {
		if (student.getName() == null || student.getName().isEmpty()) {
			throw new GenericCrudAPIStudentsException("O preenchimento do campo [nome] é obrigatório para cadastrar o estudante no sistema!");
		}
		if (student.getAge() == null) {
			throw new GenericCrudAPIStudentsException("O preenchimento do campo [idade] é obrigatório para cadastrar o estudante no sistema!");

		}
		if (student.getClassroomNumber() == null) {
			throw new GenericCrudAPIStudentsException("O preenchimento do campo [número da sala] é obrigatório para cadastrar o estudante no sistema!");

		}
		if (student.getTeachersName() == null || student.getTeachersName().isEmpty()) {
			throw new GenericCrudAPIStudentsException("O preenchimento do campo [nome do professor] é obrigatório para cadastrar o estudante no sistema!");
		}
	}

	public void deleteById(Integer id) {
		try {
			studentRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não foi possível excluir o estudante de Id: " + id + " da base de dados. " + e.getMessage());
		} catch (Exception e) {
			throw new GenericCrudAPIStudentsException("Não foi possível excluir o estudante de Id: " + id + " da base de dados. " + e.getMessage());
		}
	}
	
	public Student update(Integer id, Student student) {
		try {
			Student entity = studentRepository.getReferenceById(id);
			updateDataStudent(entity, student);
			return studentRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new EntityCrudStudentAPINotFoundException("Estudante não se encontra cadastrado na base de dados do sistema.", id);
		} catch (Exception e) {
			throw new GenericCrudAPIStudentsException("Não foi possível atualizar os dados do estudante de Id: " + id + ". " + e.getMessage());
		}
	}
	
	private void updateDataStudent(Student monitoredEntity, Student studentObj) {
		monitoredEntity.setName(studentObj.getName());
		monitoredEntity.setAge(studentObj.getAge());
		monitoredEntity.setTeachersName(studentObj.getTeachersName());
		monitoredEntity.setClassroomNumber(studentObj.getClassroomNumber());
		monitoredEntity.setFirstSemesterGrade(studentObj.getFirstSemesterGrade());
		monitoredEntity.setSecondSemesterGrade(studentObj.getSecondSemesterGrade());
	}
	
}
