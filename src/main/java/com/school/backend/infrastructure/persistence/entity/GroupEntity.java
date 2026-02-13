package com.school.backend.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToMany
    @JoinTable(
        name = "group_students",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<StudentEntity> students = new HashSet<>();

    protected GroupEntity() {}

    public GroupEntity(String name, TeacherEntity teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public void addStudent(StudentEntity student) {
        students.add(student);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public TeacherEntity getTeacher() { return teacher; }
    public Set<StudentEntity> getStudents() { return students; }
}
