package com.project.cyberedge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
public class StudentCourseDTO {

    @Data
    public static class StudentCourseRequest{
        private Integer courseId;
        private List<Integer> studentIds;
    }

    @Data
    @AllArgsConstructor
    public static class StudentCourseResponse{
        private CourseDTO.CourseResponse course;
        private List<UserDTO.UserResponseDTO> users;
    }
}
