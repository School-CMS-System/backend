package com.project.cyberedge.controller;


import com.project.cyberedge.dto.ApiResponse;
import com.project.cyberedge.dto.CourseDTO;
import com.project.cyberedge.dto.StudentCourseDTO;
import com.project.cyberedge.service.StudentCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructor/course")
public class StudentCourseAPI {
    private final StudentCourseService studentCourseService;

    @PostMapping("associate")
    public ResponseEntity<ApiResponse<Void>> login(@RequestBody StudentCourseDTO.StudentCourseRequest studentCourseRequest) {
        studentCourseService.createStudentCourse(studentCourseRequest);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .status(200)
                .message("Student associated with the course successfully!")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/associate/all")
    public ResponseEntity<ApiResponse<List<StudentCourseDTO.StudentCourseResponse>>> getAllAssociatedCourses() {
        ApiResponse<List<StudentCourseDTO.StudentCourseResponse>> response = ApiResponse.<List<StudentCourseDTO.StudentCourseResponse>>builder()
                .success(true)
                .status(200)
                .data(studentCourseService.getStudentCourses())
                .message("Student courses retrieved Successfully!")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

}
