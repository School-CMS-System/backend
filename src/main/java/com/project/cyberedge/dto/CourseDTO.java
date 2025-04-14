package com.project.cyberedge.dto;

import com.project.cyberedge.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class CourseDTO {

    @Data
    public static class CourseRequest{
        private String courseName;
        private String courseCode;
        private Integer credit;
        private List<Integer> instructorIds;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CourseResponse {
        private Integer id;
        private String courseName;
        private String courseCode;
        private Integer credit;
        private List<UserDTO.UserResponseDTO> instructors;

        public static CourseResponse fromCourse(Course course) {
            CourseResponse response = new CourseResponse();
            response.setId(course.getId());
            response.setCourseName(course.getCourseName());
            response.setCourseCode(course.getCourseCode());
            response.setCredit(course.getCredit());
            return response;
        }
    }
}
