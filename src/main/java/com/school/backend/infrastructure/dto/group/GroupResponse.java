package com.school.backend.infrastructure.dto.group;

import java.util.Set;

public class GroupResponse {
    private Long id;
    private String name;
    private String teacherName;
    private Set<String> studentNames;

    public GroupResponse(Long id, String name, String teacherName, Set<String> studentNames) {
        this.id = id;
        this.name = name;
        this.teacherName = teacherName;
        this.studentNames = studentNames;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getTeacherName() { return teacherName; }
    public Set<String> getStudentNames() { return studentNames; }
}
