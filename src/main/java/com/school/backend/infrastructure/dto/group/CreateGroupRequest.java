package com.school.backend.infrastructure.dto.group;

import java.util.Set;

public class CreateGroupRequest {
    private String name;
    private Long teacherId;
    private Set<Long> studentIds;

    public String getName() { return name; }
    public Long getTeacherId() { return teacherId; }
    public Set<Long> getStudentIds() { return studentIds; }
}
