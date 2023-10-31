package br.com.jlstudents.entities;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Schema(hidden = true)
    private Integer id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "age", nullable = false)
    private Integer age;
    
    @Column(name = "teachersName", nullable = false)
    private String teachersName;
    
    @Column(name = "classroomNumber", nullable = false)
    private Integer classroomNumber;
    
    @Column(name = "firstSemesterGrade", nullable = true)
    private double firstSemesterGrade;
    
    @Column(name = "secondSemesterGrade", nullable = true)
    private double secondSemesterGrade;

    public Student() {
	}
    
    public Student(Integer id, String name, Integer age, String teachersName, Integer classroomNumber,
			double firstSemesterGrade, double secondSemesterGrade) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.teachersName = teachersName;
		this.classroomNumber = classroomNumber;
		this.firstSemesterGrade = firstSemesterGrade;
		this.secondSemesterGrade = secondSemesterGrade;
	}

    public Integer getId() {
		return id;
	}
    
    public void setId(Integer id) {
		this.id = id;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTeachersName() {
        return teachersName;
    }

    public void setTeachersName(String teachersName) {
        this.teachersName = teachersName;
    }

    public Integer getClassroomNumber() {
        return classroomNumber;
    }

    public void setClassroomNumber(Integer classroomNumber) {
        this.classroomNumber = classroomNumber;
    }

    public double getFirstSemesterGrade() {
        return firstSemesterGrade;
    }

    public void setFirstSemesterGrade(double firstSemesterGrade) {
        this.firstSemesterGrade = firstSemesterGrade;
    }

    public double getSecondSemesterGrade() {
        return secondSemesterGrade;
    }

    public void setSecondSemesterGrade(double secondSemesterGrade) {
        this.secondSemesterGrade = secondSemesterGrade;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(id, other.id);
	}
    
}
