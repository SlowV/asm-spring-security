package com.assignment.studentmanager.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "clazz_student",
            joinColumns = @JoinColumn(name = "clazz_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students;

    public Clazz() {
    }

    public Clazz(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        if(this.students == null){
            this.students = new HashSet<>();
        }
        this.students.add(student);
        this.students.iterator().next();
    }
}
