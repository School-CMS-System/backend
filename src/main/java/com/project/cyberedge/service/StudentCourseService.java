package com.project.cyberedge.service;

import com.project.cyberedge.dto.CourseDTO;
import com.project.cyberedge.dto.StudentCourseDTO;
import com.project.cyberedge.dto.UserDTO;
import com.project.cyberedge.model.Course;
import com.project.cyberedge.model.StudentCourse;
import com.project.cyberedge.model.User;
import com.project.cyberedge.repository.CourseRepository;
import com.project.cyberedge.repository.StudentCourseRepository;
import com.project.cyberedge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentCourseService {
    private final StudentCourseRepository studentCourseRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;


    public void createStudentCourse(StudentCourseDTO.StudentCourseRequest studentCourseDTO){
        Course course = courseRepository.findById(studentCourseDTO.getCourseId()).orElseThrow(()->new RuntimeException("Course Not Found"));

        List<User> students = userRepository.findAllById(studentCourseDTO.getStudentIds());
            List<StudentCourse> studentCourses = students.stream().map(student-> new StudentCourse(course,student))
                    .toList();
            studentCourseRepository.saveAll(studentCourses);
    }

    public List<StudentCourseDTO.StudentCourseResponse> getStudentCourses(){
        List<StudentCourse> studentCourses = studentCourseRepository.findAll().stream().
                filter(studentCourse -> studentCourse.getCourse().getIsActive()).toList();
        Map<Course, List<User>> studentCourseMap = studentCourses.stream()
                .collect(Collectors.groupingBy(
                        StudentCourse::getCourse,
                        Collectors.mapping(StudentCourse::getStudent, Collectors.toList())
                ));
        return studentCourseMap.entrySet().stream()
                .map(entry -> {
                    CourseDTO.CourseResponse courseDTO = CourseDTO.CourseResponse.fromCourse(entry.getKey());
                    List<UserDTO.UserResponseDTO> userDTOs = entry.getValue().stream()
                            .map(UserDTO.UserResponseDTO::from)
                            .toList();
                    return new StudentCourseDTO.StudentCourseResponse(courseDTO, userDTOs);
                })
                .toList();
    }

}

